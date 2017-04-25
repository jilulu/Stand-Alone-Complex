package api.book;

import api.DatabaseManager;
import api.Utils;
import model.IBook;
import model.SQLBookImpl;

import java.sql.*;
import java.util.*;

/**
 * Created by jamesji on 24/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 * <p>
 * CRUD actions on the "Book" table.
 * Create: {@link #insertBook(IBook)}, {@link #insertBatchBook(Collection)}
 * Retrieve: {@link #queryBooksByTitle(String)}, {@link #queryBooksByAuthor(String)}, {@link #queryBookById(String)}, {@link #queryAllBooks()}
 * Delete: {@link #deleteBookById(int)}
 */

public class DatabaseHelper {

    // The contract: column names
    public static class Table {
        static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        static final String COLUMN_AUTHOR = "author";
        static final String COLUMN_PUBLISHER = "publisher";
        static final String COLUMN_SUMMARY = "summary";
        static final String COLUMN_ISBN = "isbn";
        static final String COLUMN_COVER_URL = "cover_url";
        static final String COLUMN_PRICE = "price";
    }

    private static volatile DatabaseHelper mHelper;

    private DatabaseHelper() {
        // suppressed default constructor
    }

    public static DatabaseHelper getInstance() {
        if (mHelper == null) {
            synchronized (DatabaseHelper.class) {
                mHelper = new DatabaseHelper();
            }
        }
        return mHelper;
    }

    /**
     * Safely execute a generic query. This is a helper method and should only be invoked from inside of this class.
     * See {@link #queryBooksByTitle(String)} for sample usage.
     *
     * @param sqlQuery          The generic query to execute. For preventing SQL injection, avoid using string concatenation
     *                          to construct the query. Instead, leave out the user-input query parameters and provide them
     *                          as a {@link List<String>} in the second parameter.
     * @param queryReplacements The user-input query parameters.
     * @return All the query results, as a collection.
     */
    private Collection<SQLBookImpl> executeGenericQuery(String sqlQuery, List<String> queryReplacements) {
        if (!sqlQuery.contains("?") || queryReplacements == null || queryReplacements.size() == 0) {
            throw new IllegalArgumentException("For preventing SQL injection, avoid using string concatenation " +
                    "to construct the query.");
        }

        Connection connection = null;
        PreparedStatement statement = null;
        final HashSet<SQLBookImpl> sqlBookHashSet = new HashSet<SQLBookImpl>();
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < queryReplacements.size(); i++) {
                statement.setString(i + 1, queryReplacements.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            SqlBookAdapter sqlBookAdapter = new SqlBookAdapter(new SqlBookAdapter.Callback() {
                @Override
                public void onBookAdapted(SQLBookImpl book) {
                    sqlBookHashSet.add(book);
                }
            });
            sqlBookAdapter.adaptResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // ignored
            }
            // close connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // ignored
            }
        }
        return sqlBookHashSet;
    }

    private Collection<SQLBookImpl> executeGenericQuery(String sqlQuery, String... queryReplacements) {
        return executeGenericQuery(sqlQuery, Arrays.asList(queryReplacements));
    }

    private int countGenericQuery(String sqlQuery, List<String> queryReplacements) {
        if (!sqlQuery.contains("?") || queryReplacements == null || queryReplacements.size() == 0) {
            throw new IllegalArgumentException("For preventing SQL injection, avoid using string concatenation " +
                    "to construct the query.");
        }
        Connection connection = null;
        PreparedStatement statement = null;
        int count = 0;
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < queryReplacements.size(); i++) {
                statement.setString(i + 1, queryReplacements.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1); // this is the count
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // ignored
            }
            // close connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // ignored
            }
        }
        return count;
    }

    private int countGenericQuery(String sqlQuery, String... queryReplacements) {
        return countGenericQuery(sqlQuery, Arrays.asList(queryReplacements));
    }

    /**
     * Retrieve all book whose title column matches %{@code title}%
     *
     * @param title Title of the book that we're querying
     * @return A collection containing all the book records. In the current implementation of the method,
     * a {@link HashSet} is returned.
     */
    public Collection<SQLBookImpl> queryBooksByTitle(String title) {
        //language=TSQL
        return executeGenericQuery("SELECT r.* FROM Shinjin.dbo.Book AS r WHERE r.title LIKE (?)",
                "%" + title + "%");
    }

    public Collection<SQLBookImpl> queryBooksByTitle(String title, int offset, int limit) {
        if (offset < 0 || limit <= 0) {
            return new HashSet<SQLBookImpl>();
        }
        //language=TSQL
        return executeGenericQuery("SELECT SortedTable.* FROM (" +
                        "SELECT r.*, ROW_NUMBER() OVER (ORDER BY ID) AS RowNum " +
                        "FROM Shinjin.dbo.Book AS r WHERE r.title LIKE (?)" +
                        ") AS SortedTable WHERE SortedTable.RowNum BETWEEN " + (offset + 1) + " AND " + (offset + limit),
                "%" + title + "%");
    }

    public int countBooksByTitle(String title) {
        //language=TSQL
        return countGenericQuery("SELECT count(*) FROM Shinjin.dbo.Book AS r WHERE r.title LIKE (?)",
                "%" + title + "%");
    }

    public Collection<SQLBookImpl> queryBooksByAuthor(String author) {
        //language=TSQL
        return executeGenericQuery("SELECT r.* FROM Shinjin.dbo.Book AS r WHERE r.author LIKE (?)",
                "%" + author + "%");
    }

    public SQLBookImpl queryBookById(String id) {
        //language=TSQL
        Collection<SQLBookImpl> sqlBooks = executeGenericQuery("SELECT r.* FROM Shinjin.dbo.Book AS r WHERE r.id=(?)", id);
        Iterator<SQLBookImpl> iterator = sqlBooks.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    /**
     * Retrieve all book from the table.
     *
     * @return All rows in the table.
     */
    public List<SQLBookImpl> queryAllBooks() {
        Connection connection = null;
        Statement statement = null;
        final List<SQLBookImpl> sqlBookList = new ArrayList<SQLBookImpl>();
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT t.* FROM Shinjin.dbo.Book AS t;");
            SqlBookAdapter sqlBookAdapter = new SqlBookAdapter(new SqlBookAdapter.Callback() {
                @Override
                public void onBookAdapted(SQLBookImpl book) {
                    sqlBookList.add(book);
                }
            });
            sqlBookAdapter.adaptResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // ignored
            }
            // close connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // ignored
            }
        }
        return sqlBookList.size() == 0 ? null : sqlBookList;
    }

    /**
     * Inset one {@link IBook} into the table.
     * When inserting multiple book, use {@link #insertBatchBook(Collection)} instead.
     *
     * @param book The book to insert into the table.
     */
    public void insertBook(IBook book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("INSERT INTO Shinjin.dbo.Book " +
                    "(title, author, publisher, summary, isbn, cover_url, price) " +
                    "VALUES ((?), (?), (?), (?), (?), (?), (?))");
            statement.setNString(1, book.getTitle());
            statement.setNString(2, book.getAuthor());
            statement.setNString(3, book.getImprint());
            statement.setNString(4, book.getSummary());
            statement.setNString(5, book.getIsbn());
            statement.setString(6, book.getCoverUrl());
            statement.setDouble(7, Utils.getFirstNumberInString(book.getPrice()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // ignored
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Bath implementation for {@link #insertBook(IBook)}.
     *
     * @param books The collection of book to insert into the table.
     */
    public void insertBatchBook(Collection<IBook> books) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("INSERT INTO Shinjin.dbo.Book " +
                    "(title, author, publisher, summary, isbn, cover_url, price) " +
                    "VALUES ((?), (?), (?), (?), (?), (?), (?))");
            for (IBook book : books) {
                statement.clearParameters();
                statement.setNString(1, book.getTitle());
                statement.setNString(2, book.getAuthor());
                statement.setNString(3, book.getImprint());
                statement.setNString(4, book.getSummary());
                statement.setNString(5, book.getIsbn());
                statement.setString(6, book.getCoverUrl());
                statement.setDouble(7, Utils.getFirstNumberInString(book.getPrice()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                // ignored
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // ignored
            }
        }
    }

    /**
     * @param id id of the row to delete
     * @return true if the row is deleted, false otherwise
     */
    public boolean deleteBookById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int columnCount = 0;
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Shinjin.dbo.Book WHERE id=(?)");
            preparedStatement.setInt(1, id);
            columnCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close statement
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                // ignored
            }

            // close connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // ignored
            }
        }
        return columnCount != 0;
    }

}

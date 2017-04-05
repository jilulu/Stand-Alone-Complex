package api;

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
    static class Table {
        static final String COLUMN_ID = "id";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_AUTHOR = "author";
        static final String COLUMN_PUBLISHER = "publisher";
        static final String COLUMN_SUMMARY = "summary";
        static final String COLUMN_ISBN = "isbn";
        static final String COLUMN_COVER_URL = "cover_url";
        static final String COLUMN_PRICE = "price";
    }

    private static volatile DatabaseHelper mHelper;

    private static final String DB_MASTER_PASSWORD = "XcMvSLUKEfBzDLmrKWnfZz3NkfFNuV3";
    private static final String JDBC_CONNECTION_CONFIG = "jdbc:sqlserver://sosdan.database.windows.net:1433;" +
            "database=Shinjin;user=jilulu@sosdan;password=" + DB_MASTER_PASSWORD + ";encrypt=true;" +
            "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

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

    Connection getDatabaseConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(JDBC_CONNECTION_CONFIG);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
        HashSet<SQLBookImpl> sqlBookHashSet = new HashSet<>();
        try {
            connection = getDatabaseConnection();
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
            connection = getDatabaseConnection();
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
     * Retrieve all books whose title column matches %{@code title}%
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
            return new HashSet<>();
        }
        //language=TSQL
        return executeGenericQuery("SELECT SortedTable.* FROM (" +
                        "SELECT r.*, ROW_NUMBER() OVER (ORDER BY ID) AS RowNum " +
                        "FROM Shinjin.dbo.Book AS r WHERE r.title LIKE (?)" +
                        ") AS SortedTable WHERE SortedTable.RowNum BETWEEN " + offset + " AND " + limit,
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
     * Retrieve all books from the table.
     *
     * @return All rows in the table.
     */
    public List<SQLBookImpl> queryAllBooks() {
        Connection connection = null;
        Statement statement = null;
        List<SQLBookImpl> sqlBookList = new ArrayList<>();
        try {
            connection = getDatabaseConnection();
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
     * When inserting multiple books, use {@link #insertBatchBook(Collection)} instead.
     *
     * @param book The book to insert into the table.
     */
    public void insertBook(IBook book) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDatabaseConnection();
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
     * @param books The collection of books to insert into the table.
     */
    public void insertBatchBook(Collection<IBook> books) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getDatabaseConnection();
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
            connection = getDatabaseConnection();
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

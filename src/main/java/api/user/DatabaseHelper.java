package api.user;

import api.DatabaseManager;
import model.IUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by jamesji on 04/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseHelper {
    private static volatile DatabaseHelper mHelper;

    private DatabaseHelper() {
        // suppressed default constructor
    }

    public static DatabaseHelper getInstance() {
        if (mHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (mHelper == null) {
                    mHelper = new DatabaseHelper();
                }
            }
        }
        return mHelper;
    }

    public static class StatusCode {

        public static final int USER_NAME_EXISTS = 400, INVALID_COMBINATION = 401, SUCCESSFUL = 200, UNDETERMINED = 300;

        private static final HashMap<Integer, String> codeMap;

        static {
            codeMap = new HashMap<>();
            codeMap.put(USER_NAME_EXISTS, "User name exists");
            codeMap.put(SUCCESSFUL, "Successful");
            codeMap.put(UNDETERMINED, "Undetermined");
            codeMap.put(INVALID_COMBINATION, "Either user name or password is invalid");
        }

        private final int code;

        public StatusCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return codeMap.get(code);
        }

        public int getCode() {
            return code;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || obj instanceof StatusCode && this.code == ((StatusCode) obj).code;
        }

        @Override
        public int hashCode() {
            return code;
        }
    }

    static class Table {
        static final String COLUMN_ID = "id";
        static final String COLUMN_USER_NAME = "user_name";
        static final String COLUMN_USER_PASSWORD = "user_password";
        static final String COLUMN_PURCHASE_RECORD = "purchase_record";
    }

    public StatusCode addUser(IUser user) {
        Connection connection = null;
        PreparedStatement statement = null;
        StatusCode statusCode = new StatusCode(StatusCode.UNDETERMINED);

        // before doing anything, check if the input is qualified to be inserted
        if (!validateUser(user)) {
            return new StatusCode(StatusCode.INVALID_COMBINATION);
        }

        // check if user_name exists in the table
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("SELECT COUNT(*) FROM Shinjin.dbo.[User] WHERE [user_name]=(?)");
            statement.setString(1, user.getUserName());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getInt(1) > 0) {
                    statusCode = new StatusCode(StatusCode.USER_NAME_EXISTS);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                // nothing
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // nothing
            }
        }

        if (statusCode.code == StatusCode.USER_NAME_EXISTS) {
            return statusCode;
        }

        // insert the row
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("INSERT INTO Shinjin.dbo.[User] VALUES (?, ?, ?)");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, null);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                statusCode = new StatusCode(StatusCode.SUCCESSFUL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }

        return statusCode;
    }

    private IUser getUserByName(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        HashSet<IUser> userHashSet = new HashSet<>();
        try {
            connection = DatabaseManager.getInstance().getDatabaseConnection();
            statement = connection.prepareStatement("SELECT * FROM Shinjin.dbo.[User] WHERE user_name=(?)");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            SqlUserAdapter adapter = new SqlUserAdapter(new SqlUserAdapter.Callback() {
                @Override
                public void onUserAdapted(IUser user) {
                    userHashSet.add(user);
                }
            });
            adapter.adaptUser(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }
        Iterator<IUser> iterator = userHashSet.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

    public IUser authUserByPassword(String username, String userpassword) {
        IUser user = getUserByName(username);
        return user == null || !user.getUserPassword().equals(userpassword) ? null : user;
    }

    public IUser authUserByToken(String username, String token) {
        IUser user = getUserByName(username);
        return user == null || !TokenUtils.getToken(user).equals(token) ? null : user;
    }

    private boolean validateUser(IUser user) {
        return user != null
                && user.getUserName() != null && user.getUserName().length() > 0
                && user.getUserPassword() != null && user.getUserPassword().length() > 0;
    }
}

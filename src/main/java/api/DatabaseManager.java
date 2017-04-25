package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jamesji on 04/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class DatabaseManager {
    private static final String DB_MASTER_PASSWORD = "XcMvSLUKEfBzDLmrKWnfZz3NkfFNuV3";
    private static final String JDBC_CONNECTION_CONFIG = "jdbc:sqlserver://sosdan.database.windows.net:1433;" +
            "database=Shinjin;user=jilulu@sosdan;password=" + DB_MASTER_PASSWORD + ";encrypt=true;" +
            "trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static volatile DatabaseManager mManager;

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance() {
        if (mManager == null) {
            synchronized (DatabaseManager.class) {
                if (mManager == null) {
                    mManager = new DatabaseManager();
                }
            }
        }
        return mManager;
    }

    public Connection getDatabaseConnection() {
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
}

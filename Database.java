package main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=University;"
            + "integratedSecurity=true;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    // No username/password needed - integratedSecurity=true uses your
    // current Windows login, which you already mapped to this database in SSMS.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
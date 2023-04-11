package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {

    private static final String DB_USER = "TestUser";
    private static final String DB_PASSWORD = "12345678";
    private static final String DB_URL = "jdbc:h2:/home/carbon/JAVAProject/HW5_JDBC/HW5_JDBC/db/MegaSoft";
    private static final String DB_Driver = "org.h2.Driver";

    private Connection connection;
    private static Database instance;
    public static Database getInstance(){
        if (instance == null) {
            instance = new Database();

            try {
                Class.forName(DB_Driver);
                instance.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema113", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

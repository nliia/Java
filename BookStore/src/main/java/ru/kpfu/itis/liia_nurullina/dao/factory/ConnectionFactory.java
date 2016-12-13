package ru.kpfu.itis.liia_nurullina.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String driverClassName = "org.postgresql.Driver";
    private final String connectionUrl = "jdbc:postgresql://localhost:5432/online_store";
    private final String dbUser = "postgres";
    private final String dbPwd = "postgres";

    private static ConnectionFactory connectionFactory = null;

    public ConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
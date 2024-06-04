package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PP114";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root1";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQL CONNECTION ERROR");
        }
        return connection;
    }

    public SessionFactory buildSessionFactory() {
        try {
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/PP114");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "root1");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "create");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            return new Configuration().setProperties(settings)
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class).buildSessionFactory();
        } catch (ExceptionInInitializerError e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
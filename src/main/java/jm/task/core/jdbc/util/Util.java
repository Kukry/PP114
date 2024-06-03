package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/PP114";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root1";

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private Session session;
    private Transaction transaction;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQL CONNECTION ERROR");
        }
        return connection;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class).buildSessionFactory();
        } catch (ExceptionInInitializerError e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public Session getSession() {
        return session;
    }

    public Session openTransactionSession() {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return session;
    }

    public void closeTransactionSession() {
        try {
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
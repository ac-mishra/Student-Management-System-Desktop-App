package org.example.util;

import org.example.config.ConnectionPool;

import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.SQLException;

public class TransactionManager {

    private Connection connection;

    public TransactionManager() throws SQLException {

        connection = ConnectionPool.getConnection();

        connection.setAutoCommit(false);

    }

    public Connection getConnection() {

        return connection;

    }

    public Savepoint createSavepoint(String name)
            throws SQLException {

        return connection.setSavepoint(name);

    }

    public void rollback()
            throws SQLException {

        connection.rollback();

    }

    public void rollback(Savepoint savepoint)
            throws SQLException {

        connection.rollback(savepoint);

    }

    public void commit()
            throws SQLException {

        connection.commit();

    }

    public void close() {

        if (connection != null) {

            try {

                connection.setAutoCommit(true);

                connection.close();

            }

            catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

    public static boolean executeTransaction(
            TransactionOperation operation
    ) {

        TransactionManager manager = null;

        try {

            manager = new TransactionManager();

            operation.execute(
                    manager.getConnection()
            );

            manager.commit();

            return true;

        }

        catch (Exception e) {

            e.printStackTrace();

            if (manager != null) {

                try {

                    manager.rollback();

                }

                catch (SQLException ex) {

                    ex.printStackTrace();

                }

            }

        }

        finally {

            if (manager != null) {

                manager.close();

            }

        }

        return false;

    }

}
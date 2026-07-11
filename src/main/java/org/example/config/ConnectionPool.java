package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(DatabaseConfig.getUrl());

        config.setUsername(DatabaseConfig.getUsername());

        config.setPassword(DatabaseConfig.getPassword());

        config.setDriverClassName(DatabaseConfig.getDriver());

        config.setMaximumPoolSize(DatabaseConfig.getPoolSize());

        config.setMinimumIdle(5);

        config.setIdleTimeout(30000);

        config.setConnectionTimeout(30000);

        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);

    }

    public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();

    }

    public static void closePool() {

        if (dataSource != null) {

            dataSource.close();

        }

    }

}
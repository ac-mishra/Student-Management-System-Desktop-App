package org.example.util;

import java.sql.Connection;

@FunctionalInterface
public interface TransactionOperation {

    void execute(Connection connection)
            throws Exception;

}
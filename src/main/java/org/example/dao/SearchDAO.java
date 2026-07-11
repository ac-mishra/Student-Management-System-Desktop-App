package org.example.dao;

import org.example.config.ConnectionPool;

import java.sql.*;
import java.util.*;

public class SearchDAO {

    public List<Map<String, Object>> search(
            String table,
            String[] columns,
            String keyword
    ) {

        List<Map<String, Object>> list =
                new ArrayList<>();

        StringBuilder sql =
                new StringBuilder(
                        "SELECT * FROM "
                                + table
                                + " WHERE "
                );

        for (int i = 0; i < columns.length; i++) {

            sql.append(columns[i]).append(" LIKE ?");

            if (i < columns.length - 1) {

                sql.append(" OR ");

            }

        }

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(
                                sql.toString()
                        )

        ) {

            for (int i = 1; i <= columns.length; i++) {

                ps.setString(i, "%" + keyword + "%");

            }

            ResultSet rs = ps.executeQuery();

            ResultSetMetaData meta =
                    rs.getMetaData();

            while (rs.next()) {

                Map<String, Object> row =
                        new LinkedHashMap<>();

                for (int i = 1;
                     i <= meta.getColumnCount();
                     i++) {

                    row.put(

                            meta.getColumnName(i),

                            rs.getObject(i)

                    );

                }

                list.add(row);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

}
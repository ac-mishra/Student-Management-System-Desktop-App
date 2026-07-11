package org.example.util;

import com.opencsv.CSVWriter;

import javax.swing.JTable;
import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {

    private CsvExporter() {
    }

    public static boolean export(
            JTable table,
            String file
    ) {

        try (CSVWriter writer =
                     new CSVWriter(
                             new FileWriter(file)
                     )) {

            String[] header =
                    new String[table.getColumnCount()];

            for (int i = 0; i < table.getColumnCount(); i++) {

                header[i] =
                        table.getColumnName(i);

            }

            writer.writeNext(header);

            for (int row = 0; row < table.getRowCount(); row++) {

                String[] data =
                        new String[table.getColumnCount()];

                for (int col = 0;
                     col < table.getColumnCount();
                     col++) {

                    Object value =
                            table.getValueAt(row, col);

                    data[col] =
                            value == null
                                    ? ""
                                    : value.toString();

                }

                writer.writeNext(data);

            }

            return true;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;

    }

}
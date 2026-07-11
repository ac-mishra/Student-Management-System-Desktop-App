package org.example.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.JTable;
import java.io.FileOutputStream;

public class PdfExporter {

    public static boolean export(
            JTable table,
            String file
    ) {

        try {

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(file)
            );

            document.open();

            PdfPTable pdfTable =
                    new PdfPTable(
                            table.getColumnCount()
                    );

            for (int i = 0;
                 i < table.getColumnCount();
                 i++) {

                pdfTable.addCell(
                        table.getColumnName(i)
                );

            }

            for (int row = 0;
                 row < table.getRowCount();
                 row++) {

                for (int col = 0;
                     col < table.getColumnCount();
                     col++) {

                    Object value =
                            table.getValueAt(row, col);

                    pdfTable.addCell(
                            value == null
                                    ? ""
                                    : value.toString()
                    );

                }

            }

            document.add(pdfTable);

            document.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}
package org.example.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTable;
import java.io.File;
import java.io.FileOutputStream;

public class ExcelExporter {

    public static boolean export(JTable table, File file) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Report");

            Row header = sheet.createRow(0);

            for (int i = 0; i < table.getColumnCount(); i++) {

                Cell cell = header.createCell(i);

                cell.setCellValue(
                        table.getColumnName(i)
                );

            }

            for (int row = 0; row < table.getRowCount(); row++) {

                Row excelRow =
                        sheet.createRow(row + 1);

                for (int col = 0;
                     col < table.getColumnCount();
                     col++) {

                    Object value =
                            table.getValueAt(row, col);

                    excelRow
                            .createCell(col)
                            .setCellValue(
                                    value == null
                                            ? ""
                                            : value.toString()
                            );

                }

            }

            for (int i = 0;
                 i < table.getColumnCount();
                 i++) {

                sheet.autoSizeColumn(i);

            }

            try (FileOutputStream out = new FileOutputStream(file)) {

                workbook.write(out);

            }

            return true;

        } catch (Exception e) {

            System.err.println("Excel Export Error : " + e.getMessage());

        }

        return false;

    }

}
package org.example.util;

import java.io.File;
import java.io.IOException;

public class DatabaseBackupUtil {

    private static final String MYSQL_BIN =
            "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    private static final String DATABASE =
            "student_management_system";

    private DatabaseBackupUtil() {
    }

    public static boolean backup(File file) {

        try {

            String command =

                    "\"" +

                            MYSQL_BIN +

                            "mysqldump.exe\" -u"

                            + USER +

                            " -p" +

                            PASSWORD +

                            " " +

                            DATABASE +

                            " -r \"" +

                            file.getAbsolutePath() +

                            "\"";

            Process process =
                    Runtime.getRuntime().exec(command);

            return process.waitFor() == 0;

        }

        catch (Exception e) {

            System.err.println(e.getMessage());

        }

        return false;

    }

    public static boolean restore(File file) {

        try {

            String command =

                    "\"" +

                            MYSQL_BIN +

                            "mysql.exe\" -u"

                            + USER +

                            " -p" +

                            PASSWORD +

                            " " +

                            DATABASE +

                            " < \"" +

                            file.getAbsolutePath() +

                            "\"";

            ProcessBuilder builder =
                    new ProcessBuilder(

                            "cmd.exe",

                            "/c",

                            command

                    );

            Process process =
                    builder.start();

            return process.waitFor() == 0;

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

}
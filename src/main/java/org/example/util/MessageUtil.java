package org.example.util;

import javax.swing.*;

public class MessageUtil {

    private MessageUtil() {
    }

    public static void info(
            java.awt.Component parent,
            String message
    ) {

        JOptionPane.showMessageDialog(
                parent,
                message,
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    public static void success(
            java.awt.Component parent,
            String message
    ) {

        JOptionPane.showMessageDialog(
                parent,
                message,
                "Success",
                JOptionPane.PLAIN_MESSAGE
        );

    }

    public static void error(
            java.awt.Component parent,
            String message
    ) {

        JOptionPane.showMessageDialog(
                parent,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

    }

    public static boolean confirm(
            java.awt.Component parent,
            String message
    ) {

        return JOptionPane.showConfirmDialog(

                parent,

                message,

                "Confirmation",

                JOptionPane.YES_NO_OPTION

        ) == JOptionPane.YES_OPTION;

    }

}
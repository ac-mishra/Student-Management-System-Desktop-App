package org.example;

import org.example.ui.login.LoginFrame;
import org.example.ui.theme.AppTheme;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        AppTheme.setup();
        SwingUtilities.invokeLater(LoginFrame::new);
    }

}
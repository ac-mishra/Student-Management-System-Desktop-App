package org.example.util;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class ThemeManager {

    private ThemeManager() {
    }

    public static void applyLightTheme() {

        try {

            UIManager.setLookAndFeel(
                    new FlatLightLaf()
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
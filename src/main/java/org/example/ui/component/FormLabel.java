package org.example.ui.component;

import org.example.ui.theme.AppFonts;

import javax.swing.*;

public class FormLabel extends JLabel {

    public FormLabel(String text) {

        super(text);

        setFont(AppFonts.NORMAL);

    }

}
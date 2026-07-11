package org.example.ui.component;

import org.example.ui.theme.AppFonts;

import javax.swing.*;

public class RoundedTextField extends JTextField {

    public RoundedTextField(int columns) {

        super(columns);

        setFont(AppFonts.NORMAL);

        putClientProperty("JComponent.roundRect", true);

    }

}
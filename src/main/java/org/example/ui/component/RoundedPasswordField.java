package org.example.ui.component;

import org.example.ui.theme.AppFonts;

import javax.swing.*;

public class RoundedPasswordField extends JPasswordField {

    public RoundedPasswordField(int columns) {

        super(columns);

        setFont(AppFonts.NORMAL);

        putClientProperty("JComponent.roundRect", true);

    }

}
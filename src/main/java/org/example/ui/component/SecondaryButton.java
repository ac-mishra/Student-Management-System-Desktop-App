package org.example.ui.component;

import org.example.ui.theme.AppFonts;

import javax.swing.*;
import java.awt.*;

public class SecondaryButton extends JButton {

    public SecondaryButton(String text) {

        super(text);

        setFont(AppFonts.BUTTON);

        setBackground(new Color(107,114,128));

        setForeground(Color.WHITE);

        setFocusPainted(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}
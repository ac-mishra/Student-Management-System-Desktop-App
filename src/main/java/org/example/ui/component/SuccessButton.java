package org.example.ui.component;

import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import java.awt.*;

public class SuccessButton extends JButton {

    public SuccessButton(String text) {

        super(text);

        setFont(AppFonts.BUTTON);

        setBackground(AppColors.SUCCESS);

        setForeground(Color.WHITE);

        setFocusPainted(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

}
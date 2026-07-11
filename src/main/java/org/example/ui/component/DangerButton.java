package org.example.ui.component;

import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DangerButton extends JButton {

    public DangerButton(String text) {

        super(text);

        setFont(AppFonts.BUTTON);

        setForeground(Color.WHITE);

        setBackground(AppColors.DANGER);

        setFocusPainted(false);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setBorder(BorderFactory.createEmptyBorder(12,25,12,25));

        setOpaque(true);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                setBackground(new Color(220,38,38));

            }

            @Override
            public void mouseExited(MouseEvent e) {

                setBackground(AppColors.DANGER);

            }

        });

    }

}
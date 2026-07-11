package org.example.ui.component;

import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SidebarButton extends JButton {

    private final Color normalColor = AppColors.SIDEBAR;
    private final Color hoverColor = AppColors.PRIMARY;
    private final Color textColor = Color.WHITE;

    public SidebarButton(String text) {

        super(text);

        initialize();

    }

    private void initialize() {

        setFont(AppFonts.BUTTON);

        setForeground(textColor);

        setBackground(normalColor);

        setHorizontalAlignment(SwingConstants.LEFT);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setFocusPainted(false);

        setBorder(new EmptyBorder(12,20,12,20));

        setContentAreaFilled(true);

        setOpaque(true);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                setBackground(hoverColor);

            }

            @Override
            public void mouseExited(MouseEvent e) {

                setBackground(normalColor);

            }

        });

    }

}
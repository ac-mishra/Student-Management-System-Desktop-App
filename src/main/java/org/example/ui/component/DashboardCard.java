package org.example.ui.component;

import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardCard extends JPanel {

    private final JLabel titleLabel;
    private final JLabel valueLabel;

    public DashboardCard(String title, String value) {

        setLayout(new BorderLayout());

        setBackground(AppColors.CARD);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.BORDER),
                new EmptyBorder(20,20,20,20)
        ));

        titleLabel = new JLabel(title);

        titleLabel.setFont(AppFonts.NORMAL);

        titleLabel.setForeground(Color.GRAY);

        valueLabel = new JLabel(value);

        valueLabel.setFont(AppFonts.TITLE);

        valueLabel.setForeground(AppColors.PRIMARY);

        add(titleLabel, BorderLayout.NORTH);

        add(valueLabel, BorderLayout.CENTER);

    }

    public void setValue(String value){

        valueLabel.setText(value);

    }

}
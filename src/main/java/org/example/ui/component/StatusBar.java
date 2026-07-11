package org.example.ui.component;

import org.example.ui.theme.AppColors;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    private final JLabel statusLabel;

    public StatusBar() {

        setLayout(new BorderLayout());

        setBackground(AppColors.CARD);

        statusLabel = new JLabel(" Ready");

        add(statusLabel, BorderLayout.WEST);

    }

    public void setStatus(String status){

        statusLabel.setText(" " + status);

    }

}
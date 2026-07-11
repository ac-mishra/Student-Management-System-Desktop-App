package org.example.ui.dashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardCard extends JPanel {

    private final JLabel title;

    private final JLabel value;

    public DashboardCard(String text) {

        setLayout(new BorderLayout());

        setBorder(new EmptyBorder(
                15,
                15,
                15,
                15
        ));

        title =
                new JLabel(text);

        value =
                new JLabel("0");

        value.setFont(

                new Font(

                        "Segoe UI",

                        Font.BOLD,

                        30

                )

        );

        add(title,
                BorderLayout.NORTH);

        add(value,
                BorderLayout.CENTER);

    }

    public void setValue(Object val) {

        value.setText(
                String.valueOf(val)
        );

    }

}
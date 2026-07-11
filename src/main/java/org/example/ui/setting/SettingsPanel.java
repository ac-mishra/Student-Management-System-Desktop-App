package org.example.ui.setting;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new MigLayout(
                        "fill,wrap 2,insets 20",
                        "[right][grow,fill]"
                )
        );

        panel.add(new JLabel("Application Theme"));

        JComboBox<String> theme =
                new JComboBox<>(
                        new String[]{
                                "Light",
                                "Dark"
                        });

        panel.add(theme,"growx");

        panel.add(new JLabel("Database Host"));

        JTextField host =
                new JTextField("localhost");

        panel.add(host,"growx");

        panel.add(new JLabel("Database Port"));

        JTextField port =
                new JTextField("3306");

        panel.add(port,"growx");

        panel.add(new JLabel("Database Name"));

        JTextField db =
                new JTextField("student_management");

        panel.add(db,"growx");

        JButton save =
                new JButton("Save Settings");

        panel.add(save,"span 2,center");

        add(panel);

    }

}
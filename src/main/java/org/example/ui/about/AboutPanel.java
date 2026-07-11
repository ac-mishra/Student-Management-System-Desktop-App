package org.example.ui.about;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    public AboutPanel() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new MigLayout(
                        "wrap 1,fill,insets 30"
                )
        );

        JLabel title =
                new JLabel(
                        "Student Management System"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JTextArea info =
                new JTextArea();

        info.setEditable(false);

        info.setOpaque(false);

        info.setText("""
Student Management System

Version : 1.0

Developed Using

• Java 21
• Swing
• FlatLaf
• MySQL
• HikariCP
• MigLayout

Features

• Student Management
• Department Management
• Course Management
• Faculty Management
• Attendance
• Marks
• Reports
• Backup & Restore
""");

        panel.add(title);

        panel.add(info,"grow");

        add(panel);

    }

}
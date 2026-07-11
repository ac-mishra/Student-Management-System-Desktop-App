package org.example.ui.backup;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class BackupPanel extends JPanel {

    public BackupPanel() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(
                new MigLayout(
                        "fill,insets 20,wrap 1",
                        "[grow]"
                )
        );

        JLabel title =
                new JLabel("Database Backup");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JButton btnBackup =
                new JButton("Backup Database");

        JButton btnRestore =
                new JButton("Restore Database");

        panel.add(title);

        panel.add(btnBackup,
                "growx,h 45");

        panel.add(btnRestore,
                "growx,h 45");

        add(panel, BorderLayout.CENTER);

    }

}
package org.example.ui.backup;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

public class BackupPanel extends JPanel {

    private final BackupController controller =
            new BackupController();
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
        btnBackup.addActionListener(e -> backupDatabase());

        btnRestore.addActionListener(e -> restoreDatabase());

        panel.add(title);

        panel.add(btnBackup,
                "growx,h 45");

        panel.add(btnRestore,
                "growx,h 45");

        add(panel, BorderLayout.CENTER);

    }
    private void backupDatabase() {

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new java.io.File("backup.sql")
        );

        int option = chooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {

            boolean success =
                    controller.backupDatabase(
                            chooser.getSelectedFile()
                    );

            JOptionPane.showMessageDialog(

                    this,

                    success
                            ? "Backup completed successfully."
                            : "Backup failed.",

                    "Backup",

                    success
                            ? JOptionPane.INFORMATION_MESSAGE
                            : JOptionPane.ERROR_MESSAGE

            );

        }

    }
    private void restoreDatabase() {

        JFileChooser chooser = new JFileChooser();

        int option = chooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {

            boolean success =
                    controller.restoreDatabase(
                            chooser.getSelectedFile()
                    );

            JOptionPane.showMessageDialog(

                    this,

                    success
                            ? "Database restored successfully."
                            : "Restore failed.",

                    "Restore",

                    success
                            ? JOptionPane.INFORMATION_MESSAGE
                            : JOptionPane.ERROR_MESSAGE

            );

        }

    }

}
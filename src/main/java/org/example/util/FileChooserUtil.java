package org.example.util;

import javax.swing.*;
import java.io.File;

public class FileChooserUtil {

    public static File saveFile(
            String extension,
            String description
    ) {

        JFileChooser chooser =
                new JFileChooser();

        chooser.setDialogTitle(
                "Save File"
        );

        chooser.setFileFilter(

                new javax.swing.filechooser.FileNameExtensionFilter(
                        description,
                        extension
                )

        );

        if (chooser.showSaveDialog(null)
                == JFileChooser.APPROVE_OPTION) {

            File file =
                    chooser.getSelectedFile();

            if (!file.getName()
                    .endsWith("." + extension)) {

                file =
                        new File(
                                file.getAbsolutePath()
                                        + "." + extension
                        );

            }

            return file;

        }

        return null;

    }

}
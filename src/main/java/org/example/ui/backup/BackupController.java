package org.example.ui.backup;

import org.example.util.DatabaseBackupUtil;

import java.io.File;

public class BackupController {

    public boolean backupDatabase(File file) {

        return DatabaseBackupUtil.backup(file);

    }

    public boolean restoreDatabase(File file) {

        return DatabaseBackupUtil.restore(file);

    }

}
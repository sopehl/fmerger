package com.sopehl.impl;

import com.sopehl.model.Scm;
import com.sopehl.spec.Archivable;
import com.sopehl.spec.VersionControl;
import com.sopehl.util.FileUtils;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.File;

public class FileArchive implements Archivable {

    private static final Log LOG = new SystemStreamLog();

    private String sourceOutputPath;

    private String targetOutputPath;

    private String fileName;

    private String extension;

    private VersionControl versionControl;

    private Scm scm;

    public FileArchive(String sourceOutputPath, String targetOutputPath, String fileName, String extension, VersionControl versionControl, Scm scm) {
        this.sourceOutputPath = sourceOutputPath;
        this.targetOutputPath = targetOutputPath;
        this.fileName = fileName;
        this.extension = extension;
        this.versionControl = versionControl;
        this.scm = scm;
    }

    @Override
    public Boolean archive() {
        String targetOutputPath = this.getTargetOutputPath();
        String fileNameWithExtension = fileName + "." + extension;
        LOG.info("File archive/release is started. Uploaded to file://" + targetOutputPath);
        File source = new File(FileUtils.checkEndOfPath(sourceOutputPath) + fileNameWithExtension);

        String archiveFileNameWithExtension = fileName + "." + extension;
        LOG.info("File moved success: " + source.getAbsolutePath() + " --> " + targetOutputPath + archiveFileNameWithExtension);
        boolean isMoved = FileUtils.moveFile(source, targetOutputPath, archiveFileNameWithExtension);

        if (isMoved & this.scm != null) {
            LOG.info("SCM processing.");
            versionControl.commitAndPush(this.scm);
        }

        return isMoved;
    }

    private String getTargetOutputPath() {
        return targetOutputPath != null ? FileUtils.checkEndOfPath(targetOutputPath) :
                FileUtils.checkEndOfPath(sourceOutputPath) + "archive" + File.separator;
    }

}

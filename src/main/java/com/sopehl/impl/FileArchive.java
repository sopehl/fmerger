package com.sopehl.impl;

import com.sopehl.spec.Archivable;
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

    public FileArchive() {
    }

    public FileArchive(String sourceOutputPath, String targetOutputPath, String fileName, String extension) {
        this.sourceOutputPath = sourceOutputPath;
        this.targetOutputPath = targetOutputPath;
        this.fileName = fileName;
        this.extension = extension;
    }

    @Override
    public Boolean archive() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean snapshot() {
        String targetOutputPath = this.getTargetOutputPath();
        String fileNameWithExtension = fileName + "." + extension;
        LOG.info("File snapshot is started. Uploaded to file://" + targetOutputPath);
        File source = new File(FileUtils.checkEndOfPath(sourceOutputPath) + fileNameWithExtension);

        String snapshotFileNameWithExtension = fileName + "-SNAPSHOT." + extension;
        LOG.info("File moved success: " + source.getAbsolutePath() + " --> " + targetOutputPath + snapshotFileNameWithExtension);
        return FileUtils.moveFile(source, targetOutputPath, snapshotFileNameWithExtension);
    }


    private String getTargetOutputPath() {
        return targetOutputPath != null ? FileUtils.checkEndOfPath(targetOutputPath) :
                FileUtils.checkEndOfPath(sourceOutputPath) + "snapshot" + File.separator;
    }

}

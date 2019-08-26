package com.sopehl.impl;

import com.sopehl.spec.Archivable;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

public class FileArchive implements Archivable {

    private static final Log LOG = new SystemStreamLog();

    public Boolean archive() {
        LOG.info("File archiving is started. Uploaded to file://{filePath}");
        return false;
    }

}

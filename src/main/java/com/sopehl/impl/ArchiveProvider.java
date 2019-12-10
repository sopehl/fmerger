package com.sopehl.impl;

import com.sopehl.spec.Archivable;

public class ArchiveProvider {

    private Archivable archiver;

    public ArchiveProvider(Archivable archiver) {
        this.archiver = archiver;
    }

    public Boolean archive() {
        boolean isSnapshot = Boolean.parseBoolean(System.getProperty("archive", "false"));
        if (isSnapshot) {
            archiver.archive();
        }

        return isSnapshot;
    }
}

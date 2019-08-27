package com.sopehl.impl;

import com.sopehl.spec.Archivable;

public class ArchiveProvider {

    private Archivable archiver;

    public ArchiveProvider(Archivable archiver) {
        this.archiver = archiver;
    }

    public Boolean archive() {
        throw new UnsupportedOperationException();
    }

    public Boolean snapshot() {
        boolean isSnapshot = Boolean.parseBoolean(System.getProperty("snapshot", "false"));
        if (isSnapshot) {
            archiver.snapshot();
        }

        return isSnapshot;
    }
}

package com.sopehl.model;

public class Archive {

    private String protocol;

    private String path;

    private String snapshotPath;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSnapshotPath() {
        return snapshotPath;
    }

    public void setSnapshotPath(String snapshotPath) {
        this.snapshotPath = snapshotPath;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "protocol='" + protocol + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

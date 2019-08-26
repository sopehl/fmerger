package com.sopehl.model;

public class Archive {

    private String protocol;

    private String path;

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

    @Override
    public String toString() {
        return "Archive{" +
                "protocol='" + protocol + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

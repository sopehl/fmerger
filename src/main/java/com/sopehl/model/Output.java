package com.sopehl.model;

import java.io.File;

public class Output {

    private String protocol;

    private String path;

    private String extension;

    private String finalNamePrefix;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPath() {
        if (!this.path.endsWith(File.separator)) {
            path = path.concat(File.separator);
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        if (this.extension == null) {
            return "fmerger";
        }
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFinalNamePrefix() {
        return finalNamePrefix;
    }

    public void setFinalNamePrefix(String finalNamePrefix) {
        this.finalNamePrefix = finalNamePrefix;
    }

    @Override
    public String toString() {
        return "Output{" +
                "type='" + protocol + '\'' +
                ", path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}

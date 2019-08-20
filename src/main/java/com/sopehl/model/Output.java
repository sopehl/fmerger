package com.sopehl.model;

import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

public class Output {

    private String type;

    private String path;

    private String extension;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Output{" +
                "type='" + type + '\'' +
                ", path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}

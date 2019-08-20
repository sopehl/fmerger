package com.sopehl.impl;

import com.sopehl.spec.Writeable;
import com.sopehl.util.FileUtils;

import java.io.File;

public class FileWriter implements Writeable {

    private File file;

    public FileWriter(File file) {
        this.file = file;
    }

    public void write(String content) {
        FileUtils.write(file, content);
    }

}

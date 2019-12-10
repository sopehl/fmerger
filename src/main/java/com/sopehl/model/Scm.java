package com.sopehl.model;

import org.apache.commons.lang3.StringUtils;

public class Scm {

    private String connection;

    private String comment = "[fmerger-commit]";

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCommentPrefix() {
        String commentPrefix = System.getProperty("commentPrefix");
        if (StringUtils.isNotEmpty(commentPrefix)) {
            return commentPrefix.replace(" ", "-");
        }

        return this.comment.replace(" ", "-");
    }

    public void setCommentPrefix(String comment) {
        this.comment = comment;
    }
}

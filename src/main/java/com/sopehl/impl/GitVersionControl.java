package com.sopehl.impl;

import com.sopehl.model.Scm;
import com.sopehl.spec.VersionControl;
import com.sopehl.util.ProcessUtils;

public class GitVersionControl implements VersionControl {

    @Override
    public void commitAndPush(Scm scm) {
        ProcessUtils.execute("git status");
        ProcessUtils.execute("git add .");
        ProcessUtils.execute("git commit -m " + scm.getCommentPrefix());
        ProcessUtils.execute("git push " + scm.getConnection() + " refs/heads/master:refs/heads/master");
    }
}

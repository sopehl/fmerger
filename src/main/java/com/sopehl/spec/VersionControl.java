package com.sopehl.spec;

import com.sopehl.model.Scm;

public interface VersionControl {

    void commitAndPush(Scm scm);

}

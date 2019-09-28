package com.app.constants.enums;

public enum ApprovalStatus {

    PENDING(1),
    APPROVED(2),
    NOT_APPROVED(3);

    int status;

    ApprovalStatus (int status) {
        this.status = status;
    }

    public int status () { return this.status; }
}

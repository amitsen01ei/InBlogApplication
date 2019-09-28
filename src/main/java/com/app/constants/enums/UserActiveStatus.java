package com.app.constants.enums;

public enum UserActiveStatus {
    ACTIVE(true),
    INACTIVE(false);

    private boolean status;

    UserActiveStatus (boolean status) {
        this.status = status;
    }

    public boolean status () {
        return this.status;
    }
}

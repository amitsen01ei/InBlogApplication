package com.app.constants.enums;

public enum Roles {

    ADMIN(1),
    BLOGGER(2);

    private int roleId;

    Roles(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId () {
        return this.roleId;
    }
}

package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 21-11-17.
 */

public class RoleModel {

    private long codeRole;
    private String name;
    private String permission;

    public RoleModel(long codeRole, String name, String permission) {
        this.codeRole = codeRole;
        this.name = name;
        this.permission = permission;
    }

    public long getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(long codeRole) {
        this.codeRole = codeRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

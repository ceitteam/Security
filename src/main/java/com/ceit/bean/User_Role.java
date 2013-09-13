package com.ceit.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-11
 * Time: 下午10:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "User_Group_Role_tb")
public class User_Role {
    @Column(name = "user_id",nullable = true)
    private long user_id;

    private String permission;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

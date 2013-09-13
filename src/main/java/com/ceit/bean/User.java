package com.ceit.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: 王康
 * Date: 13-9-7
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
/*用户*/
@Entity
@Table(name = "user_tb")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    @Column(name = "user_account",nullable = false,unique = true)
    private String user_account;
    @Column(name = "user_password",nullable = false)
    private String user_password;
    private Set<User_Role>  user_group_roles = new HashSet<User_Role>();
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (user_account != null ? !user_account.equals(user.user_account) : user.user_account != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return user_account != null ? user_account.hashCode() : 0;
    }
}

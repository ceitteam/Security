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
/*角色*/
@Entity
@Table(name = "role_tb")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long role_id;
    @Column(name="role_name",nullable = false,unique = true)
    private String role_name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<User>();
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name="role_permission_tb",joinColumns = {@JoinColumn(name = "role_permission_tb_role_id",referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_permission_tb_permission_id",referencedColumnName = "permission_name")})
    private Set<Permission> permissions = new HashSet<Permission>();

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

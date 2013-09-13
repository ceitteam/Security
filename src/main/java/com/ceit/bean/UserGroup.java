package com.ceit.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-10
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
/*用户组*/
@Entity
@Table(name = "usergroup_tb")
public class UserGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long usergroup_id;
    private String description;//描述
    private String group_name;
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "usergroup_permission_tb",joinColumns =
            {@JoinColumn(name = "usergroup_permission_tb_usergroup_id",referencedColumnName = "usergroup_id")},inverseJoinColumns =
            {@JoinColumn(name = "usergroup_permission_tb_permission_name",referencedColumnName = "permission_name")})
    private Set<Permission> permissions = new HashSet<Permission>();

}

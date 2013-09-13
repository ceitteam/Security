package com.ceit.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-10
 * Time: 下午4:30
 * To change this template use File | Settings | File Templates.
 */
/*对应权限*/
@Entity
@Table(name = "permission_tb")
public class Permission implements Serializable {
    @Id
    private String permission_name;
    @Column(name = "group_id",nullable = false)
    private long group_id;
    private String resourceId;
    private String operate_name;

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getOperate_name() {
        return operate_name;
    }

    public void setOperate_name(String operate_name) {
        this.operate_name = operate_name;
    }
}

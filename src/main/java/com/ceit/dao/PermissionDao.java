package com.ceit.dao;

import com.ceit.bean.Permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-11
 * Time: 下午8:36
 * To change this template use File | Settings | File Templates.
 */
public interface PermissionDao {
    public List<Permission> findAllPermission();
}

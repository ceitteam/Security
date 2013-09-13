package com.ceit.dao;

import com.ceit.bean.User;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-11
 * Time: 下午7:32
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public User userlogin(String name,String password);
    public User getUserByName(String name);
}

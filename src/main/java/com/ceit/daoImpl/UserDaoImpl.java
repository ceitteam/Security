package com.ceit.daoImpl;

import com.ceit.bean.User;
import com.ceit.dao.UserDao;
import com.ceit.Tools.MD5Tools;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-11
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */
public class UserDaoImpl extends BaseDaoImpl<User,Long> implements UserDao {

    @Override
    public User userlogin(String name, String password) {
        String[] args = {"user_account"};
        String sql = "from User where user_account=:user_account";
        User user = this.getObject(sql,args,name);
        if (user == null)
            return null;
        MD5Tools md5Toos = new MD5Tools();
        if (user.getUser_password().equals(md5Toos.getMD5_Str(password)))
        {
              return user;
        }
        else
        {
            return null;
        }
    }

    @Override
    public User getUserByName(String name) {
        String[] args = {"user_account"};
        String sql = "from User where user_account=:user_account";
        User user = this.getObject(sql,args,name);
       return user;

    }
}

package com.ceit.serviceImpl;

import com.ceit.bean.Permission;
import com.ceit.bean.Role;
import com.ceit.dao.UserDao;
import com.ceit.daoImpl.UserDaoImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午1:03
 * To change this template use File | Settings | File Templates.
 */
/*加载用户权限的类
* 只有当用户验证成功的时候才能够调用这个类
* */
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("用户已经登录");
        UserDao userDao = new UserDaoImpl();
        if (s == null)
            throw  new UsernameNotFoundException("用户名为空");
        com.ceit.bean.User user_common = userDao.getUserByName(s);
        if (user_common == null)
            throw new UsernameNotFoundException("没有这个用户");
        Set<String> roles = this.loadRoles(user_common);
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Iterator<String> iterator = roles.iterator();
        while (iterator.hasNext())
        {
            authorities.add(new SimpleGrantedAuthority(iterator.next()));
        }
        User user = new User(user_common.getUser_account(), user_common.getUser_password(), true, true, true, true, authorities);
        return user;
    }
    //载入用户所有的权限
    private Set<String> loadRoles( com.ceit.bean.User user_common) {
        Set<String> permissions_str = new HashSet<String>();
        Set<Role> set_roles = null;
       // set_roles  = user_common.getRoles();
        if (set_roles == null)
            set_roles   = new HashSet<Role>();
        Iterator<Role> iterator= set_roles.iterator();
        while (iterator.hasNext())
        {
            Set<Permission> permissions = iterator.next().getPermissions();
            Iterator< Permission > iterator_Permission = permissions.iterator();
            while(iterator_Permission.hasNext())
                permissions_str.add(iterator_Permission.next().getPermission_name() );
        }
        permissions_str.add("ROLE_LOGIN");
        return permissions_str;
    }
}

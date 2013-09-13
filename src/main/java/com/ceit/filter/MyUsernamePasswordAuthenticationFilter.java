package com.ceit.filter;

import com.ceit.bean.User;
import com.ceit.dao.UserDao;
import com.ceit.daoImpl.UserDaoImpl;
import com.ceit.Tools.PatternTools;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
/*主要使用来负责用户登录模块的*/
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
     /*当用户验证失败的时候可以报这个异常*/
    /*throw  new AuthenticationServiceException("用户名或者密码错误");*/
    private UserDao userDao;
    private PatternTools patternTools;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("用户进行登录 ");
        User user = null;

        //user = this.userLogin(request,response);
        if (user ==null)
        {
            throw  new AuthenticationServiceException("用户名或者密码错误");
        }
         System.out.println("用户登录成功 "+user.getUser_account());
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(user.getUser_account(), user.getUser_password());
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    //普通用户
    private User userLogin(HttpServletRequest request, HttpServletResponse response) {
        String cardid = request.getParameter("cardid");
        String password = request.getParameter("password");
        User user = null;
        if (!patternTools.NullPatern(cardid,password))
            return null;
        userDao = new UserDaoImpl();
        user = userDao.userlogin(cardid,password);
        if (user!=null)
            request.getSession().setAttribute("user",user);
        return user;
    }
}

package com.ceit.serviceImpl;

import com.ceit.bean.Permission;
import com.ceit.bean.Resource;
import com.ceit.dao.PermissionDao;
import com.ceit.daoImpl.PermissionDaoImpl;
import com.ceit.service.UrlMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-16
 * Time: 下午12:12
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource  {
    private UrlMatcher urlMatcher;
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    public MyInvocationSecurityMetadataSourceService() {
        loadResourceDefine();
    }
    /*系统加载的时候自动调用*/
    private void loadResourceDefine() {
        //To change body of created methods use File | Settings | File Templates.
       /* urlMatcher = new AntUrlPathMatcher();
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute>  attributes = new ArrayList<ConfigAttribute>();
        PermissionDao permissionDao = new PermissionDaoImpl();
        List<Permission> allPermission =permissionDao.findAllPermission();
        for (Permission permission : allPermission)
        {
            ConfigAttribute configAttribute = new SecurityConfig(permission.getPermission_name());
            Set<Resource> urls = permission.getFunctions();
            Iterator<Function> iterator = urls.iterator();
            while (iterator.hasNext())
            {
                 String url = iterator.next().getFunction_url();
                 if (resourceMap.containsKey(url))    //如果包含就进行添加
                 {
                     resourceMap.get(url).add(configAttribute);
                 }   else
                 {
                     attributes.add(configAttribute);
                     resourceMap.put(url,attributes);
                 }
            }
        }*/
    }
    //用户请求的url
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        System.out.println(object) ;
        String url = String.valueOf(object);
        String new_url = "";
        int url_index = url.indexOf("?");
        if (url_index != -1)
        {
            new_url = url.substring(0,url_index);
        }else
        {
            new_url = url;
        }
        Iterator<String> stringIterator = resourceMap.keySet().iterator();
        while (stringIterator.hasNext())
        {
            String iteurl = stringIterator.next();
             if (urlMatcher.pathMatchesUrl(new_url,iteurl))
             {
                 return resourceMap.get(iteurl);
             }
        }
        Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
        returnCollection.add(new SecurityConfig("ROLE_NO_USER"));
        //这里不能返回null否则无法进行权限的验证
        return  returnCollection;

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return  null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

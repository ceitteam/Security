package com.ceit.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-11
 * Time: 下午7:28
 * To change this template use File | Settings | File Templates.
 */
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {
    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest,servletResponse,filterChain);
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try{
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }

    }
    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.filterInvocationSecurityMetadataSource;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public FilterInvocationSecurityMetadataSource getFilterInvocationSecurityMetadataSource() {
        return filterInvocationSecurityMetadataSource;
    }

    public void setFilterInvocationSecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
    }
}


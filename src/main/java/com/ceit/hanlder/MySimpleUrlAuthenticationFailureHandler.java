package com.ceit.hanlder;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-4
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
/*在UsernamePasswordAuthenticationFilter登录失败后抛出异常之后调用*/
public class MySimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String head = request.getHeader("x-requested-with");
        PrintWriter writer = response.getWriter();
        if ("XMLHttpRequest".equals(head))
        {
            response.setHeader("Content-Type","Content-Type=application/json");
            Map<String ,String> message = new HashMap<String,String>();
            message.put("message","login_error");

            writer.write(new JSONObject(message).toString());
        }
        else
        {
            writer.write("login_error");
        }
        writer.flush();
        writer.close();
    }
}

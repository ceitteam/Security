package com.ceit.hanlder;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

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
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
/*在UsernamePasswordAuthenticationFilter登录成功之后的调用*/
public class MySimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String head = request.getHeader("x-requested-with");
        PrintWriter writer = response.getWriter();
       if ("XMLHttpRequest".equals(head))
       {
           response.setHeader("Content-Type","Content-Type=application/json");
           Map<String ,String> message = new HashMap<String,String>();
           message.put("message","login_success");

           writer.write(new JSONObject(message).toString());
       }
       else
       {
           writer.write("login_success");
       }
        writer.flush();
        writer.close();
    }
}

package com.ceit.serviceImpl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-15
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 * 当没有权限的时候进行调用
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
        String head = request.getHeader("x-requested-with");
        //如果是ajax请求
        if ("XMLHttpRequest".equals(head))
        {
            try {
                JSONObject jsonObject =
                        new JSONObject(new HashMap<String,String>().put("message","logout"));
                PrintWriter printWriter = response.getWriter();
                printWriter.write(jsonObject.toString());
                printWriter.flush();
                printWriter.close();
            } catch (JSONException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }  else
        {
             response.sendError(403);
        }

    }

}

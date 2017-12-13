package com.jt.blog.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jt.blog.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by Tim on 2016/8/27.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final static String URL_ENCODER_CODE = "UTF-8";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String ip = getRemoteHost(request);
        String url = request.getRequestURI();
        if (ip.equals("127.0.0.1") && (url.indexOf("back/register") > -1)) {
            return true;
        }
        if (user == null) {
            PrintWriter out = response.getWriter();
            if (isAjax(request)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sessionStatus", "IsTimeOut");
                out.print(jsonObject.toString());
            } else {
                out.println("<html>");
                out.println("<script>");
                out.println("window.open ('" + request.getContextPath() + "/back/login?target=" + URLEncoder.encode(uri, URL_ENCODER_CODE) + "','_top')");
                out.println("</script>");
                out.println("</html>");
            }
            return false;
        }
        return true;
    }

    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}

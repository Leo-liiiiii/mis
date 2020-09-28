package com.ujiuye.mis.interceptor;

import com.ujiuye.mis.pojo.Employee;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiLe
 * @create 2020-08-27 20:31
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
     Employee active= (Employee) request.getSession().getAttribute("active");
     if(ObjectUtils.isEmpty(active)){
         //request.getRequestDispatcher("login.html").forward(request,response);
         response.sendRedirect("login.html");
         System.out.println("拦截了");
          return  false;
     }
        System.out.println("方形了");
        System.out.println("======================"+active);
         return true;
    }
}

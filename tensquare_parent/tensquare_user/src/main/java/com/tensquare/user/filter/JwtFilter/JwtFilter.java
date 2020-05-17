package com.tensquare.user.filter.JwtFilter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhang
 * @Date 2020/5/17 19:54
 * @Version 1.0
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器！");
        String authorization = request.getHeader("Authorization");
        if (authorization != null && !"".equals(authorization) && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null){
                    if ("admin".equals(claims.get("roles"))){
                        request.setAttribute("admin_claims", claims);
                    }
                    if ("user".equals(claims.get("roles"))){
                        request.setAttribute("user_claims", claims);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}

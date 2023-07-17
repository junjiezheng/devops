package com.cherrypicks.devops.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cherrypicks.devops.service.UserService;
import com.cherrypicks.devops.util.JwtTokenUtil;
 
 
/**
 * 对于任何一个传入的请求，都会执行doFilterInternal。
 * 检查请求是否具有有效的token。
 * 如果token有效，在上下文中设置Authentication，
 * 表明当前用户通过身份验证。
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
    @Autowired
    private UserService jwtUserDetailsService;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String username = null;
        String token = null;
 
        // token一般又称之为"Bearer token"，以Bearer开头
        // 截取纯粹的token
        String BEARER = "Bearer ";
        if (header != null && header.startsWith(BEARER)) {
            token = header.substring(BEARER.length());//从Bearer 之后开始截取
            username = JwtTokenUtil.getUsernameFromToken(token);
            System.out.println(username + " token:" + token);
        }
 
        //拿到token后验证
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails =jwtUserDetailsService.loadUserByUsername(username);
 
            // 如果token有效，配置Spring Security授权
            if (JwtTokenUtil.validateToken(token, userDetails)) {
                System.out.println(username + " token验证通过");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //当前用户已经授权，授权认证信息传递给Spring Security配置.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
 
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println(username + " 已授权");
        }
 
        filterChain.doFilter(request, response);
    }
}

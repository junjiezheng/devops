package com.cherrypicks.devops.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cherrypicks.devops.service.UserService;
 
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 密码管理
     */
    @Autowired
    private JwtPasswordEncoder jwtPasswordEncoder;
 
    @Autowired
    private UserService jwtUserDetailsService;
 
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
 
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 本例不需要CSRF
        http.csrf().disable()
                // 排除/auth。
                // 对于请求授权的/auth不需要授权，放行。
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                //其余的所有请求均需要认证授权
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()//错误处理
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                //本例不需要维护有状态的session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
        // 对于任何一个传入的请求加入一个token过滤器，验证。
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
 
    /**
     * 配置AuthenticationManager使其知道从那里加载用户认证信息
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(jwtUserDetailsService)
                    .passwordEncoder(jwtPasswordEncoder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 假设这一部分接口是公开开放的，不需要token即可访问。
     * 这部分客户端http请求不拦截
     * 排除。
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/index**",
                        "/home/**",
                		"/swagger*/**",// /swagger-ui/index.html,swagger-resources/**
                        "/webjars*/**",// /webjars/**
                        "/*/api-docs");// /v2/api-docs
    }
 
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

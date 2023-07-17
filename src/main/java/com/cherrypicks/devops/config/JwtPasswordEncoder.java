package com.cherrypicks.devops.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
 
 
/**
 * 注意，此处是明文，实际场景时候要加密
 */
@Component
public class JwtPasswordEncoder extends BCryptPasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }
 
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword为空！");
        }
 
        if (encodedPassword == null || encodedPassword.length() == 0) {
            throw new IllegalArgumentException("encodedPassword为空");
        }
        return encodedPassword.equals(rawPassword);
    }
}
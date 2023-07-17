package com.cherrypicks.devops.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cherrypicks.devops.dto.JwtRequest;
import com.cherrypicks.devops.dto.JwtResponse;
import com.cherrypicks.devops.service.UserService;
import com.cherrypicks.devops.util.JwtTokenUtil;
import com.cherrypicks.devops.vo.ResultVO;

@RestController
public class LoginController extends BaseController{
	
	@Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private UserService jwtInMemoryUserDetailsService;
	
	/**
     * 验证用户名和密码。
     * 如果验证通过，创建token并将其返回给客户端
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/auth",consumes="application/json")
    public ResultVO<Object> createAuthenticationToken(final HttpServletRequest request,final HttpServletResponse response,
    		@RequestBody JwtRequest jwtRequest) {
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
 
        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
 
        UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = JwtTokenUtil.generateToken(userDetails);
        
        return ResultVO.info(new JwtResponse(token));
    }
	
	@GetMapping(value="/api")
    public ResultVO<Object> api(final HttpServletRequest request,final HttpServletResponse response) {
        Map<String,String> map=new HashMap<>();
        map.put("page", "api");
        try {
			
		} catch (Exception e) {
			logger.error("程序异常, 详细信息:{}", e.getLocalizedMessage() , e);
		}
        return ResultVO.info(map);
    }
 
	@GetMapping(value="/index")
    public ResultVO<Object> index(final HttpServletRequest request,final HttpServletResponse response) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("page", "index");
        return ResultVO.info(map);
    }
 
	@GetMapping(value="/home")
    public ResultVO<Object> home(final HttpServletRequest request,final HttpServletResponse response) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("page", "home");
        return ResultVO.info(map);
    }
    
}

package com.cherrypicks.devops.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	
	private static final String DEMO_RESOURCE_ID = "own";
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	//这个位置我们将Client客户端注册信息写死，后面章节我们会讲解动态实现
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	//指定客户端详细信息将存储在内存中
        clients.inMemory()
                .withClient("oauth1")
                .secret(passwordEncoder.encode("123456")) // Client 账号、密码。
                .resourceIds(DEMO_RESOURCE_ID)
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(864000)
                .scopes("all","read","write") // 客户端的授权范围 Scope
                //.autoApprove(true)//false跳转到授权页面
                .redirectUris("http://www.baidu.com") // 配置回调地址，选填。
                .authorizedGrantTypes("authorization_code","refresh_token")
                .authorities("CLIENT"); // 授权类型
                
    }
    
    //配置授权服务器的安全性
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    		oauthServer
                .tokenKeyAccess("permitAll()")//获取令牌（token）的请求的访问权限
                .checkTokenAccess("permitAll()")//校验令牌（token）的请求的访问权限
                .allowFormAuthenticationForClients();//配置允许客户端进行表单身份验证
    }
    
}

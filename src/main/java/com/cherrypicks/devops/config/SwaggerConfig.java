package com.cherrypicks.devops.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Rei.zheng
 * @date 2021年03月26日
 * @fileName SwaggerConfig.java
 */
@Configuration  //Swagger2配置类
@EnableOpenApi //开启Swagger2
@Profile({"local", "dev", "prod"})
public class SwaggerConfig {
	
	public static final String HEADER_AUTHORIZATION="Authorization";
	
	/**
	  * 创建API应用
      * apiInfo() 增加API相关信息
      * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
      *采用指定扫描的包路径来定义指定要建立API的目录。
      * @return
     */
    @Bean
    public Docket docket() {
    	return new Docket(DocumentationType.OAS_30)
    			.enable(true)
    			.useDefaultResponseMessages(false)
    			.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cherrypicks.devops.controller"))
                .paths(PathSelectors.any())               
                .build() 
                .protocols(getProtocols("https", "http"))                
                .securitySchemes(securitySchemes())                
                .securityContexts(securityContexts());
    	
     }
    
    private HashSet<String> getProtocols(String https, String http) {
        HashSet<String> set = new HashSet<>();
        set.add(https);
        set.add(http);
        return set;
    }
    
    private List<SecurityScheme> securitySchemes() {
    	List<SecurityScheme> securitySchemes = new ArrayList<>(0);
    	securitySchemes.add(
    			new ApiKey(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION, "header"));
    	return securitySchemes;
    }
  
    private List<SecurityContext> securityContexts() {
    	List<SecurityContext> securityContexts = new ArrayList<>(0);
    	securityContexts.add(SecurityContext.builder()
    			.securityReferences(defaultAuth())
    			.operationSelector(operationContext -> true)
    			.build());
    	return securityContexts;
    }
    
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("read", "write");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        
        List<SecurityReference> securityReferences = new ArrayList<>(0);
        securityReferences.add(new SecurityReference(HEADER_AUTHORIZATION, authorizationScopes));
        return securityReferences;
    }
    
    private ApiInfo apiInfo() {
    	Contact contact=new Contact("Cherrypicks","","");
        return new ApiInfoBuilder()
                .title("ELK")
                .description("ELK backend api")
                .termsOfServiceUrl("")
                .contact(contact)
                .version("1.0")
                .build();
    }
}

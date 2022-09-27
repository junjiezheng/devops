package com.cherrypicks.devops.config;

import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Rei.zheng
 * @date 2021年03月26日
 * @fileName SwaggerConfig.java
 */
@Configuration  //Swagger2配置类
@EnableSwagger2 //开启Swagger2
@Profile({"dev", "prod"})
public class SwaggerConfig {
	
	/**
	  * 创建API应用
      * apiInfo() 增加API相关信息
      * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
      *采用指定扫描的包路径来定义指定要建立API的目录。
      * @return
     */
    @Bean
    public Docket docket() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.useDefaultResponseMessages(false)
    			.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cherrypicks.devops.contoller"))
                .paths(PathSelectors.any())               
                .build()                
                .protocols(getProtocols("https", "http"));
     }
    
    private HashSet<String> getProtocols(String https, String http) {
        HashSet<String> set = new HashSet<>();
        set.add(https);
        set.add(http);
        return set;
    }
    
    private ApiInfo apiInfo() {
    	Contact contact=new Contact("Cherrypicks","","");
        return new ApiInfoBuilder()
                .title("DevOps API")
                .description("Development test phase api")
                .termsOfServiceUrl("")
                .contact(contact)
                .version("1.0")
                .build();
    }
}

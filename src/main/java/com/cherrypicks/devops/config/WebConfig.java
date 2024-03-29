package com.cherrypicks.devops.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
			"com.cherrypicks.deveops.service",
			"com.cherrypicks.deveops.controller"})
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new InternalResourceViewResolver());
    }
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
		
		registry.addResourceHandler("/static/**").addResourceLocations(
				"classpath:/static/");	
	}
	
}

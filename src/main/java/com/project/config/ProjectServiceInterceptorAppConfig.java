package com.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ProjectServiceInterceptorAppConfig implements WebMvcConfigurer {
	
	@Autowired
	RequestProcessingTimeInterceptor requestInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(requestInterceptor);
	}
}

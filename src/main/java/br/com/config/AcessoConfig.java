package br.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.controller.ClienteInterceptor;
import br.com.controller.FuncionarioInterceptor;

@Configuration
public class AcessoConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor((HandlerInterceptor) new ClienteInterceptor())
			.addPathPatterns(new String[] {"/Client", "/Client/**"}).excludePathPatterns("/css/**", "/icons/**", "/js/**");
		
		registry.addInterceptor((HandlerInterceptor) new FuncionarioInterceptor())
			.addPathPatterns(new String[] {"/Admin", "/Admin/**"}).excludePathPatterns("/css/**", "/icons/**", "/js/**");
	}
	
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/admin/**")
	      .addResourceLocations("classpath:/static/");
	  }
}

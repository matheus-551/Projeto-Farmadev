package br.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.controller.AutorizadorInterceptor;

@Configuration
public class AcessoConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor((HandlerInterceptor) new AutorizadorInterceptor())
			.addPathPatterns(new String[] {"/admin", "/admin/**"}).excludePathPatterns("/css/**", "/icons/**", "/js/**");
	}
	
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/admin/**")
	      .addResourceLocations("classpath:/static/");
	  }
}

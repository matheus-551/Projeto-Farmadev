package br.com.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafTemplateConfig {

	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		return templateEngine;
	}
	
	@Bean
	public ClassLoaderTemplateResolver htmlTemplateResolver() {
		//Configuração para busca do template através do thymeleaf
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		//informa em qual pasta buscar o template
		emailTemplateResolver.setPrefix("/templates/");
		//informa a extenção do arquivo a ser buscado
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
		emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
		return emailTemplateResolver;
	}
}

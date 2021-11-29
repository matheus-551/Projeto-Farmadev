package br.com.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import br.com.model.Acesso;
import br.com.model.Cliente;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
		
	@Autowired
	private AcessoService acessoService;
	
	public void sendMail(Cliente cliente) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
						StandardCharsets.UTF_8.name());
			
			Context context = new Context();
			context.setVariable("cliente", cliente);
			
			Acesso acesso = this.acessoService.BuscaPorId(cliente.getAcesso().getId());
			context.setVariable("Acesso", acesso);
			
			String html = this.templateEngine.process("BodyEmail", context);
			String Subj = "Suporte Farmadev";
			
			helper.setTo(cliente.getEmail());
			helper.setFrom(cliente.getEmail());
			helper.setSubject(Subj);
			helper.setText(html, true);
			
			emailSender.send(message);
			
			System.out.println("Email Enviado com sucesso");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro ao tentar enviar o email");
		}
	}
}

package br.com.ottimizza.apiemailsendler.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.apiemailsendler.config.CustomPropertyConfig;
import br.com.ottimizza.apiemailsendler.domain.models.Mail;
import br.com.ottimizza.apiemailsendler.services.EmailSendlerService;

@RestController
@RequestMapping("/mail/v1")
public class MailController {
	
	private EmailSendlerService service;
	private CustomPropertyConfig config;
	
	public MailController(EmailSendlerService service, CustomPropertyConfig config) {
		this.service = service;
		this.config = config;
	}
	
	@GetMapping
	public String sendMail() throws MessagingException {
		Mail mail = getMail();
		service.sendMail(mail);
		return "Check your email";
	}

	private Mail getMail() {
		Mail mail = new Mail();
		mail.setFrom(config.emailFrom);
		mail.setTo("dev.diogo@gmail.com");
		mail.setSubject("meu email de teste "+System.currentTimeMillis());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("templateVariable", "Email com SES aws");
		mail.setModel(model);
		return mail;
	}
	
	

}

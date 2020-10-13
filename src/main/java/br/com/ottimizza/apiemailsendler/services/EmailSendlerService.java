package br.com.ottimizza.apiemailsendler.services;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


import br.com.ottimizza.apiemailsendler.domain.models.Mail;

@Service
public class EmailSendlerService {

	private JavaMailSender javaMailSender;
//	private SpringTemplateEngine engine;
	
	public EmailSendlerService(JavaMailSender javaMailSender, SpringTemplateEngine engine) {
		this.javaMailSender = javaMailSender;
//		this.engine = engine;
	}
	
	@Async
	public void sendMail(Mail mail) throws MessagingException {
		MimeMessage message = getMimeMessage(mail);
		javaMailSender.send(message);
	}

	private MimeMessage getMimeMessage(Mail mail) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = 
				new MimeMessageHelper(
						message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
						StandardCharsets.UTF_8.name());
		
		Context context = new Context();
		context.setVariables(mail.getModel());
//		String html = engine.process("email-template", context);
		
		helper.setTo(mail.getTo());
//		helper.setText(html, true);
		helper.setText("teste teste 1");
		helper.setSubject(mail.getSubject());
		helper.setFrom(mail.getFrom());
				
		return message;
	}
}




































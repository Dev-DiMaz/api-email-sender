package br.com.ottimizza.apiemailsender.services;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.model.MessageRejectedException;

import br.com.ottimizza.apiemailsender.configs.mail.AwsMailPropertyConfig;
import br.com.ottimizza.apiemailsender.domain.models.Mail;

@Service
public class MailService {

	private JavaMailSender javaMailSender;
	private AwsMailPropertyConfig config;
	
	public MailService(JavaMailSender javaMailSender, AwsMailPropertyConfig config) {
		this.javaMailSender = javaMailSender;
		this.config = config;
	}
	
	public void validateAndSendEmail(Mail mail) throws MessagingException {
		this.validateMailMessage(mail);
		
		this.sendMail(mail);
		
	}
	
	private void validateMailMessage(Mail mail) throws IllegalArgumentException {
        if (mail.getTo() == null || mail.getTo().equals("")) {
            throw new IllegalArgumentException("Attribute 'to' cannot be null or blank.");
        }

        if (mail.getSubject() == null || mail.getSubject().equals("")) {
            throw new IllegalArgumentException("Attribute 'subject' cannot be null or blank.");
        }

        if (mail.getBody() == null || mail.getBody().equals("")) {
            throw new IllegalArgumentException("Attribute 'body' cannot be null or blank.");
        }
    }
	
	@Async
	public void sendMail(Mail mail) throws MessagingException {
		MimeMessage message = getMimeMessage(mail);
		javaMailSender.send(message);
	}

	private MimeMessage getMimeMessage(Mail mail) throws MessagingException, MessageRejectedException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = 
				new MimeMessageHelper(
						message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
						StandardCharsets.UTF_8.name());
		
		helper.setFrom(config.emailFrom);

        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        
        helper.setTo(mail.getTo());
        
		return message;
	}

}



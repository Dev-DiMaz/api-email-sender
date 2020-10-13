package br.com.ottimizza.apiemailsendler.controllers;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.apiemailsendler.domain.models.Mail;
import br.com.ottimizza.apiemailsendler.domain.responses.SuccessResponse;
import br.com.ottimizza.apiemailsendler.services.MailService;

@RestController
@RequestMapping("/api/v1/emails")
public class MailController {
	
	private MailService service;
	
	public MailController(MailService service) {
		this.service = service;
	}
	
	@PostMapping
    public ResponseEntity<?> sendMail(@RequestBody Mail mail) throws MessagingException {
        service.validateAndSendEmail(mail);
        return ResponseEntity.ok(new SuccessResponse("success"));
    }

}

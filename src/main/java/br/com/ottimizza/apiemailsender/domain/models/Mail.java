package br.com.ottimizza.apiemailsender.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
	
	private String to;
	
	private String subject;
	
	private String body;

}

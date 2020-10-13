package br.com.ottimizza.apiemailsendler.domain.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
	
	private String from;
	private String to;
	private String subject;
//	private Map<String, Object> model;

}

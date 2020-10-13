package br.com.ottimizza.apiemailsendler.configs.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.ottimizza.apiemailsendler.configs.YamlPropertySourceFactory;

@Component
@ConfigurationProperties(prefix = "yaml")
@PropertySource(value = "classpath:aws-config.yml", factory = YamlPropertySourceFactory.class)
public class CustomPropertyConfig {
	
	@Value("${aws.ses.mailFrom}")
	public String emailFrom;
	
	@Value("${aws.ses.awsAccessKey}")
	public String awsAccessKey;
	
    @Value("${aws.ses.awsSecretKey}")
    public String awsSecretKey;

}

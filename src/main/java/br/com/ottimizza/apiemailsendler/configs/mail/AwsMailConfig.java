package br.com.ottimizza.apiemailsendler.configs.mail;

import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

@Configuration
public class AwsMailConfig {
	
	private CustomPropertyConfig config;
	
	public AwsMailConfig(final CustomPropertyConfig config) {
		this.config = config;
	}
	
	@Bean
	public AmazonSimpleEmailService amazonSimpleEmailService() {
		
		
		return AmazonSimpleEmailServiceClientBuilder.standard()
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(config.awsAccessKey, config.awsSecretKey)))
				.withRegion(Regions.CA_CENTRAL_1)
				.build();
	}
	
	@Bean
	public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
		return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
	}
	
}

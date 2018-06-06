package br.com.cadastro.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.cadastro.services.DBServices;
import br.com.cadastro.services.EmailService;
import br.com.cadastro.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBServices dbServices;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException{
		dbServices.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService(){
		return new MockEmailService();
	}
	
	@Bean
    public JavaMailSenderImpl mailSender(){
		return new JavaMailSenderImpl();
	}
}

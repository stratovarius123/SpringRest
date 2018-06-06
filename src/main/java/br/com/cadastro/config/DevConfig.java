package br.com.cadastro.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cadastro.services.DBServices;
import br.com.cadastro.services.EmailService;
import br.com.cadastro.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DBServices dbServices;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String valorChave;
	
	
	@Bean
	public boolean instantiateDatabase() throws ParseException{
		dbServices.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService(){
		return new SmtpEmailService();
	}
}

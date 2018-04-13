package br.com.cadastro.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cadastro.services.DBServices;

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
}

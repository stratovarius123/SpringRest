package br.com.cadastro.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="Campo Obrigatorio!")
	@Email
	private String email;

	
	public EmailDTO(){
	}
	
	public EmailDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

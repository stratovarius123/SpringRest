package br.com.cadastro.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import br.com.cadastro.domain.Cliente;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	public ClienteDTO (){}

	private Integer id;
	
	@NotNull(message="Campo Obrigatorio!")
	@Length(min=5,max=120,message="O campo deve ter entre 5 e 120 caracteres")
	private String nome;
	
	@NotNull(message="Campo Obrigatorio!")
	@Email
	private String email;
	
	public ClienteDTO(Cliente cliente){
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

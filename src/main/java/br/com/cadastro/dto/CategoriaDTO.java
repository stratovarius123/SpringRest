package br.com.cadastro.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.cadastro.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Campo Obrigatorio!")
	@Length(min=5, max=80, message="O campo deve ter entre 5 e 80 caracteres")
	private String categoria;
	
	public CategoriaDTO(Categoria categoria){
		this.id = categoria.getId();
		this.categoria = categoria.getCategoria();
	}
	
	public CategoriaDTO(){
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}

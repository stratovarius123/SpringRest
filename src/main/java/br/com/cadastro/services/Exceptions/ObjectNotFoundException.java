package br.com.cadastro.services.Exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String mensagem){
		super(mensagem);
	}

}

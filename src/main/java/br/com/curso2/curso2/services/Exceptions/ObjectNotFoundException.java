package br.com.curso2.curso2.services.Exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String mensagem){
		super(mensagem);
	}

}

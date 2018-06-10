package br.com.cadastro.services.Exceptions;

public class AuthorizationExcetion extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthorizationExcetion(String mensagem){
		super(mensagem);
	}

}

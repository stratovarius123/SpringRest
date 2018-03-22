package br.com.cadastro.services.Exceptions;

public class DataIntegrityViolationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationException(String mensagem){
		super(mensagem);
	}

}

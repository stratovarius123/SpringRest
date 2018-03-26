package br.com.cadastro.resources.Exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<FieldMessage>();
	
	public ValidationError(int status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String messagem) {
		list.add(new FieldMessage(fieldName, messagem));
	}

}

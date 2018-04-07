package br.com.cadastro.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cadastro.domain.Cliente;
import br.com.cadastro.domain.enums.TipoCliente;
import br.com.cadastro.dto.ClienteNewDTO;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.resources.Exception.FieldMessage;
import br.com.cadastro.services.validation.utils.BR;
public class ClienteUpdateValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
 
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo() == TipoCliente.PESSOA_FISICA.getCodigo() && !BR.isValidCPFfinal(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOucnpj","CPF inválido"));
		}		

		if(objDto.getTipo() == TipoCliente.PESSOA_JURIDICA.getCodigo() && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOucnpj","CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());	
		
		if(aux != null){
			list.add(new FieldMessage("email", "email ja existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}


}
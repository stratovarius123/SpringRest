package br.com.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Estado;
import br.com.cadastro.domain.enums.Perfil;
import br.com.cadastro.repositories.EstadoRepository;
import br.com.cadastro.security.UserSS;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository estadoRespository;
	
	public Estado find(Integer id){
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
			throw new AuthorizationServiceException("Acesso Negado");
		}
		
		Estado estado = estadoRespository.findOne(id);

		if(estado == null) {
			throw new ObjectNotFoundException("Estado não encontrado");
		} 
		return estado;
	}
	
	public List<Estado> findAll(){
		return estadoRespository.findAllByOrderByNome();
	}

}


package br.com.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Cidade;
import br.com.cadastro.domain.enums.Perfil;
import br.com.cadastro.repositories.CidadeRepository;
import br.com.cadastro.security.UserSS;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	public List<Cidade> find(Integer id){
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
			throw new AuthorizationServiceException("Acesso Negado");
		}
		
		List<Cidade> cidades = cidadeRepository.findCidades(id);

		if(cidades == null) {
			throw new ObjectNotFoundException("cidade não encontrado");
		} 
		return cidades;
	}
	
	public List<Cidade> findAll(){
		return cidadeRepository.findAll();
	}
	
	
	
}

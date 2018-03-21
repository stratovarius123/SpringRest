package br.com.cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Cliente;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteDao;
	
	public Cliente buscar(Integer id){
		Cliente cliente = clienteDao.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return cliente;
	}
}

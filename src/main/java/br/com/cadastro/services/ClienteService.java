package br.com.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.domain.Cliente;
import br.com.cadastro.dto.CategoriaDTO;
import br.com.cadastro.dto.ClienteDTO;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteDao;
	
	public Cliente find(Integer id){
		Cliente cliente = clienteDao.findOne(id);
		if(cliente == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return cliente;
	}
	
	public List<Cliente> findAll(){
		return clienteDao.findAll();
	}
	
	public Cliente save(Cliente cliente){
		cliente.setId(null);
		return clienteDao.save(cliente);
	}
	
	public Cliente update(Cliente cliente){
		Cliente cli = this.find(cliente.getId());
		updateData(cli,cliente);
		return  clienteDao.save(cli);
	}
	
	public void delete(Integer id){
		try{
			clienteDao.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new br.com.cadastro.services.Exceptions.DataIntegrityViolationException("Não é possivel deletar pois a produto vinculado a essa categoria");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage,String orderBy,String direction ){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return clienteDao.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDto){
		return new Cliente(clienteDto.getId(), clienteDto.getNome(),clienteDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newCliente, Cliente cliente){
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
}

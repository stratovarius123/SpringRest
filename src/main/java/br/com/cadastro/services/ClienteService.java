package br.com.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Cidade;
import br.com.cadastro.domain.Cliente;
import br.com.cadastro.domain.Endereco;
import br.com.cadastro.domain.enums.Perfil;
import br.com.cadastro.domain.enums.TipoCliente;
import br.com.cadastro.dto.ClienteDTO;
import br.com.cadastro.dto.ClienteNewDTO;
import br.com.cadastro.repositories.CidadeRepository;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.repositories.EnderecoRepository;
import br.com.cadastro.security.UserSS;
import br.com.cadastro.services.Exceptions.AuthorizationExcetion;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteDao;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	public Cliente find(Integer id){
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
			throw new AuthorizationServiceException("Acesso Negado");
		}
		
		Cliente cliente = clienteDao.findOne(id);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Cliente não encontrado");
		} 
		return cliente;
	}
	
	public List<Cliente> findAll(){
		return clienteDao.findAll();
	}
	
	public Cliente findByEmail(String email){
		
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) || !email.equals(email)){
				throw new AuthorizationExcetion("Acesso Negado");
		}

		Cliente clienteEncontrado = clienteDao.findByEmail(email);
		if(clienteEncontrado == null) {
			throw new ObjectNotFoundException("Cliente não encontrado");
		} 
		return clienteEncontrado;
	}
	
	public Cliente save(Cliente cliente){
		cliente.setId(null);
		cliente = clienteDao.save(cliente);
		enderecoRepository.save(cliente.getEnderecos());
		return cliente;
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
		return new Cliente(clienteDto.getId(), clienteDto.getNome(),null,clienteDto.getEmail(), null, null);
	}
	public Cliente fromDTO(ClienteNewDTO clienteNewDto){
		Cliente cliente =  new Cliente(null, clienteNewDto.getNome(),bcrypt.encode(clienteNewDto.getSenha()),clienteNewDto.getEmail(), clienteNewDto.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDto.getTipo()));
		Cidade cidade = cidadeRepository.findOne(clienteNewDto.getCidadeId());
		Endereco endereco = new Endereco(null, clienteNewDto.getLogradouro(), clienteNewDto.getNumero(), clienteNewDto.getComplemento(), clienteNewDto.getBairro(), clienteNewDto.getCep(), cliente,cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteNewDto.getTelefone1());
		return cliente;
	}
	
	private void updateData(Cliente newCliente, Cliente cliente){
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
}

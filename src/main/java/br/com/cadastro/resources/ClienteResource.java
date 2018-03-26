package br.com.cadastro.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastro.domain.Cliente;
import br.com.cadastro.dto.ClienteDTO;
import br.com.cadastro.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	ClienteService clienteRepository;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Cliente cliente = clienteRepository.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteDTO cad){
		Cliente fromDTO = clienteRepository.fromDTO(cad);
		fromDTO = clienteRepository.save(fromDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cad.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}" , method=RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO cad, @PathVariable Integer id){
		cad.setId(id);
		Cliente fromDTO = clienteRepository.fromDTO(cad);
		clienteRepository.update(fromDTO);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> Clientes = clienteRepository.findAll();
		return ResponseEntity.ok().body(Clientes);
	}
	
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage
	(@RequestParam(value="page", defaultValue="0")Integer page, 
	 @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
	 @RequestParam(value="orderBy", defaultValue="nome")String orderBy,
	 @RequestParam(value="direction", defaultValue="ASC")String direction ){
		Page<ClienteDTO> listaDTO;
		Page<Cliente> Clientes = clienteRepository.findPage(page, linesPerPage, orderBy,direction);
		listaDTO = Clientes.map(cat -> new ClienteDTO(cat));		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
	
}

package br.com.cadastro.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.domain.Cidade;
import br.com.cadastro.domain.Estado;
import br.com.cadastro.dto.CidadeDTO;
import br.com.cadastro.dto.EstadoDTO;
import br.com.cadastro.services.CidadeService;
import br.com.cadastro.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	EstadoService estadoService;
	
	@Autowired
	CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> estados = estadoService.findAll();
		List<EstadoDTO> estadosDto = estados.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estadosDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades" ,method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		 List<Cidade> cidades = cidadeService.find(estadoId);
		List<CidadeDTO> cidadesDto = cidades.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDto);
	}
	
}

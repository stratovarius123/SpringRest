package br.com.cadastro.resources;

import java.net.URI;
import java.util.List;

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

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.dto.CategoriaDTO;
import br.com.cadastro.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> insert(@RequestBody Categoria cad){
		cad = categoriaService.save(cad);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cad.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}" , method=RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@RequestBody Categoria cad, @PathVariable Integer id){
		cad.setId(id);
		categoriaService.update(cad);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<CategoriaDTO> categorias = categoriaService.findAll();
		return ResponseEntity.ok().body(categorias);
	}
	
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage
	(@RequestParam(value="page", defaultValue="0")Integer page, 
	 @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
	 @RequestParam(value="orderBy", defaultValue="nome")String orderBy,
	 @RequestParam(value="direction", defaultValue="ASC")String direction ){
		Page<CategoriaDTO> listaDTO;
		Page<Categoria> categorias = categoriaService.findPage(page, linesPerPage, orderBy,direction);
		listaDTO = categorias.map(cat -> new CategoriaDTO(cat));		
		return ResponseEntity.ok().body(listaDTO);
	}
}

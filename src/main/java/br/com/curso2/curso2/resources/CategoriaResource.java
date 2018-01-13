package br.com.curso2.curso2.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso2.curso2.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar(){
		Categoria c1 = new Categoria(1,"Informatica");
		Categoria c2 = new Categoria(2,"escritorio");

		List<Categoria> categorias = new ArrayList<Categoria>();
		
		categorias.add(c1);
		categorias.add(c2);
		
		return categorias;
	}
}

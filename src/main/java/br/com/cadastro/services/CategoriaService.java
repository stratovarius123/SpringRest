package br.com.cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.repositories.CategoriaRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository CategoriaDao;
	
	public Categoria find(Integer id){
		Categoria categoria = CategoriaDao.findOne(id);
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return categoria;
	}
	
	public Categoria save(Categoria cad){
		cad.setId(null);
		return CategoriaDao.save(cad);
	}
}

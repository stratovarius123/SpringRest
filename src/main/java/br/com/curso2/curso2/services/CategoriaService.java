package br.com.curso2.curso2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso2.curso2.domain.Categoria;
import br.com.curso2.curso2.repositories.CategoriaRepository;
import br.com.curso2.curso2.services.Exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository CategoriaDao;
	
	public Categoria buscar(Integer id){
		Categoria categoria = CategoriaDao.findOne(id);
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return categoria;
	}
	
}

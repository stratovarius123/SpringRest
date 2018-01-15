package br.com.curso2.curso2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.curso2.curso2.domain.Categoria;
import br.com.curso2.curso2.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository CategoriaDao;
	
	public Categoria buscar(Integer id){
		return CategoriaDao.findOne(id);
	}
	
}

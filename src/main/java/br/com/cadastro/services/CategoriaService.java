package br.com.cadastro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.dto.CategoriaDTO;
import br.com.cadastro.repositories.CategoriaRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository CategoriaDao;
	
	public List<CategoriaDTO> findAll(){
		List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();
		List<Categoria> listaCategoria = CategoriaDao.findAll();
		listaDTO = listaCategoria.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return listaDTO;
	}
	
	public Categoria find(Integer id){
		Categoria categoria = CategoriaDao.findOne(id);
		if(categoria == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return categoria;
	}
	
	public Categoria save(Categoria cat){
		cat.setId(null);
		return CategoriaDao.save(cat);
	}
	
	public Categoria update(Categoria cat){
		Categoria categoria = this.find(cat.getId());
		return  CategoriaDao.save(categoria);
	}
	
	public void delete(Integer id){
		try{
			CategoriaDao.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new br.com.cadastro.services.Exceptions.DataIntegrityViolationException("Não é possivel deletar pois a produto vinculado a essa categoria");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage,String orderBy,String direction ){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return CategoriaDao.findAll(pageRequest);
	}
	
}

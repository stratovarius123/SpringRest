package br.com.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.domain.Produto;
import br.com.cadastro.repositories.CategoriaRepository;
import br.com.cadastro.repositories.ProdutoRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoDao;
		
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id){
		Produto produto = produtoDao.findOne(id);
		if(produto == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return produto;
	}
	
	public Page<Produto> search (String nome, List<Integer> ids,Integer page, Integer linesPerPage,String orderBy,String direction ){
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return produtoDao.findDistinctByNomeContainingAndCategoriaIn(nome, categorias, pageRequest);
	}
}

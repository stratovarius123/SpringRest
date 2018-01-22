package br.com.curso2.curso2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.curso2.curso2.domain.Categoria;
import br.com.curso2.curso2.domain.Produto;
import br.com.curso2.curso2.repositories.CategoriaRepository;
import br.com.curso2.curso2.repositories.ProdutoRepository;

@SpringBootApplication
public class Curso2Application implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Curso2Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		Categoria categoria = new Categoria(null,"Informatica");
		Categoria categoria2 = new Categoria(null,"Escritorio");

		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
	
		categoria.getProduto().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProduto().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(categoria);
		p2.getCategorias().addAll(Arrays.asList(categoria,categoria2));
		p3.getCategorias().add(categoria);
		
		
		categoriaRepository.save(Arrays.asList(categoria,categoria2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
	}
}

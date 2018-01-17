package br.com.curso2.curso2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.curso2.curso2.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}

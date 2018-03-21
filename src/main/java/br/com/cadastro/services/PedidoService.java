package br.com.cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Pedido;
import br.com.cadastro.repositories.PedidoRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository PedidoDao;
	
	public Pedido buscar(Integer id){
		Pedido pedido = PedidoDao.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return pedido;
	}
	
}

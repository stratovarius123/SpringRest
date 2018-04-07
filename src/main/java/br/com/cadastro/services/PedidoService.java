package br.com.cadastro.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.ItemPedido;
import br.com.cadastro.domain.PagamentoComBoleto;
import br.com.cadastro.domain.Pedido;
import br.com.cadastro.domain.enums.EstadoPagamento;
import br.com.cadastro.repositories.ItemPedidoRepository;
import br.com.cadastro.repositories.PagamentoRepository;
import br.com.cadastro.repositories.PedidoRepository;
import br.com.cadastro.repositories.ProdutoRepository;
import br.com.cadastro.services.Exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoDao;
	
	@Autowired
	BoletoService boletoService;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public Pedido buscar(Integer id){
		Pedido pedido = pedidoDao.findOne(id);
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return pedido;
	}

	public Pedido save(Pedido pedido) {
		pedido.setId(null);
		pedido.setData(new Date());
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto){
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,pedido.getData());
		}
		
		Pedido pedidoSalvo = pedidoDao.save(pedido);
		pagamentoRepository.save(pedidoSalvo.getPagamento());
		
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0);
			item.setPreco(produtoRepository.findOne(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		} 
		
		itemPedidoRepository.save(pedido.getItens());
		
		return pedidoSalvo;
	
	}
	
}

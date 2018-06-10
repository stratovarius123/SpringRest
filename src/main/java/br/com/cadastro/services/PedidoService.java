package br.com.cadastro.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Cliente;
import br.com.cadastro.domain.ItemPedido;
import br.com.cadastro.domain.PagamentoComBoleto;
import br.com.cadastro.domain.Pedido;
import br.com.cadastro.domain.enums.EstadoPagamento;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.repositories.ItemPedidoRepository;
import br.com.cadastro.repositories.PagamentoRepository;
import br.com.cadastro.repositories.PedidoRepository;
import br.com.cadastro.repositories.ProdutoRepository;
import br.com.cadastro.security.UserSS;
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
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EmailService emailService;
	
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
		pedido.setCliente(clienteRepository.findOne(pedido.getCliente().getId()));
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
			item.setProduto(produtoRepository.findOne(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		} 
		
		itemPedidoRepository.save(pedido.getItens());
		emailService.sendOrderConfirmationHtmlEmail(pedidoSalvo);
		
		return pedidoSalvo;
	
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage,String orderBy,String direction ){
		UserSS user = UserService.authenticated();
		if(user == null){
			throw new AuthorizationServiceException("Acesso Negado");
		}
		Cliente cliente = clienteRepository.findOne(user.getId());
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		
		return pedidoDao.findByCliente(cliente, pageRequest);
	}
	
}

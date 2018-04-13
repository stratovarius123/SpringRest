package br.com.cadastro.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.domain.Categoria;
import br.com.cadastro.domain.Cidade;
import br.com.cadastro.domain.Cliente;
import br.com.cadastro.domain.Endereco;
import br.com.cadastro.domain.Estado;
import br.com.cadastro.domain.ItemPedido;
import br.com.cadastro.domain.Pagamento;
import br.com.cadastro.domain.PagamentoComBoleto;
import br.com.cadastro.domain.PagamentoComCartao;
import br.com.cadastro.domain.Pedido;
import br.com.cadastro.domain.Produto;
import br.com.cadastro.domain.enums.EstadoPagamento;
import br.com.cadastro.domain.enums.TipoCliente;
import br.com.cadastro.repositories.CategoriaRepository;
import br.com.cadastro.repositories.CidadeRepository;
import br.com.cadastro.repositories.ClienteRepository;
import br.com.cadastro.repositories.EnderecoRepository;
import br.com.cadastro.repositories.EstadoRepository;
import br.com.cadastro.repositories.ItemPedidoRepository;
import br.com.cadastro.repositories.PagamentoRepository;
import br.com.cadastro.repositories.PedidoRepository;
import br.com.cadastro.repositories.ProdutoRepository;

@Service
public class DBServices {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException{
		Categoria categoria = new Categoria(null,"Informatica");
		Categoria categoria2 = new Categoria(null,"Escritorio");

		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);

		categoria.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		categoria2.getProdutos().addAll(Arrays.asList(p2));
		
	
		p1.getCategorias().addAll(Arrays.asList(categoria));
		p2.getCategorias().addAll(Arrays.asList(categoria,categoria2));
		p3.getCategorias().addAll(Arrays.asList(categoria));
		
		
		categoriaRepository.save(Arrays.asList(categoria,categoria2));
		
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);

		Cidade c2 = new Cidade(null, "Sao Paulo",est2);
		
		Cidade c3 = new Cidade(null, "campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		est2.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.save(est1);
		estadoRepository.save(est2);
		
		cidadeRepository.save(c1);
		cidadeRepository.save(c2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com","417033258010", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("996112374","35967612"));

				
		Endereco e1 = new Endereco(null, "Rua Flores","300","apto. 303","jardim","38220834",cli1,c1);
		Endereco e2 = new Endereco(null, "Av. Matos","105","Sala 800","Centro","38777012",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
	}
}

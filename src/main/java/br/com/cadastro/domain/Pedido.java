package br.com.cadastro.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Pedido(){
	}
	
	public Pedido(Integer id, Date data, Cliente cliente, Endereco endereco) {
		super();
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.endereco = endereco;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/mm/yyyy hh:mm")
	private Date data;
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="pedido")
	private Pagamento pagamento;
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public double getValorTotal(){
		double soma = 0.0;
	
		for (ItemPedido itemPedido : itens) {
			soma = itemPedido.getSubtotal() + soma;
		} 
		
		return soma;
	}
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		Integer result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat formataNumeroPorDinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat formatadaData  = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido Número : ");
		builder.append(getId());
		builder.append(", Instante :");
		builder.append(formatadaData.format(getData()));
		builder.append(", Cliente :");
		builder.append(getCliente().getNome());
		builder.append(", Situação pagamento : ");
		builder.append(getPagamento().getEstadoPagamento().getDescricao());
		builder.append("\n Detalhes : \n");

		for (ItemPedido itemPedido : getItens()) {
			builder.append(itemPedido.toString());
		} 
		
		builder.append("Valor Total: ");
		builder.append(formataNumeroPorDinheiro.format(getValorTotal()));
		return builder.toString();
	}
}
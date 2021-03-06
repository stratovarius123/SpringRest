package br.com.cadastro.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPk id = new ItemPedidoPk();
	
	
	private double desconto;
	private double quantidade;
	private double preco;
	
	public double getSubtotal(){
		return (preco - desconto) * quantidade;
	}
	
	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public ItemPedido(){
		
	}
	
	public void setPedido(Pedido pedido){
		id.setPedido(pedido);
	}
	
	public void setProduto(Produto produto){
		id.setProduto(produto);
	}
	
	public Produto getProduto(){
		return id.getProduto();
	}
	
	@JsonIgnore
	public Pedido getPedido(){
		return id.getPedido();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ItemPedido(Pedido pedido,Produto produto, double desconto, double quantidade, double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@Override
	public String toString() {
		NumberFormat formataNumeroPorDinheiro = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qtde: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(formataNumeroPorDinheiro.format(getPreco()));
		builder.append(", SubTotal: ");
		builder.append(formataNumeroPorDinheiro.format(getSubtotal()));
		builder.append("\n");
		return builder.toString();
	}
}

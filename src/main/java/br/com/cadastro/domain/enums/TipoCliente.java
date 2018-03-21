package br.com.cadastro.domain.enums;

public enum TipoCliente {
	
	PESSOA_FISICA(1, "Pessoa Fisica"),
	PESSOA_JURIDICA(2,"Pessoa Juridica");

	private int codigo;
	private String descricao;
	
	private TipoCliente(int cod, String desc){
		this.codigo = cod;
		this.descricao = desc;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod){
		if(cod == null){
			return null;
		}
		for(TipoCliente x : TipoCliente.values()){
			if(cod.equals(x.getCodigo())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID Invalido");
	}
}

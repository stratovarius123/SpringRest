package br.com.cadastro.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROL_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	private int codigo;
	private String descricao;
	
	private Perfil(int cod, String desc){
		this.codigo = cod;
		this.descricao = desc;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod){
		if(cod == null){
			return null;
		}
		for(Perfil x : Perfil.values()){
			if(cod.equals(x.getCodigo())){
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID Invalido");
	}
}

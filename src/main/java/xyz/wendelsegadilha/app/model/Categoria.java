package xyz.wendelsegadilha.app.model;

public enum Categoria {
	
	NOVO(1, "Novo"),
	USADO(2, "Usado"),
	RECONDICIONADO(3, "Recondicionado");
	
	private int codigo;
	private String descricao;
	
	private Categoria(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

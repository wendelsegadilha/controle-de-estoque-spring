package xyz.wendelsegadilha.app.util;

import java.util.List;

public class PaginacaoUtil<T> {
	
	private int tamanho;
	private int pagina;
	private long totalDePagina;
	private List<T> registros;
	private String direcao;
	
	
	
	public PaginacaoUtil(int tamanho, int pagina, long totalDePagina, List<T> registros, String direcao) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePagina = totalDePagina;
		this.registros = registros;
		this.direcao = direcao;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalDePagina() {
		return totalDePagina;
	}

	public List<T> getRegistros() {
		return registros;
	}
	 public String getDirecao() {
		return direcao;
	}
}

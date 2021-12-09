package xyz.wendelsegadilha.app.model;

import java.io.Serializable;

public class Home implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long qtd_produto;
	private long qtd_cliente;
	private long qtd_fornecedor;
	
	public long getQtd_produto() {
		return qtd_produto;
	}
	public void setQtd_produto(long qtd_produto) {
		this.qtd_produto = qtd_produto;
	}
	public long getQtd_cliente() {
		return qtd_cliente;
	}
	public void setQtd_cliente(long qtd_cliente) {
		this.qtd_cliente = qtd_cliente;
	}
	public long getQtd_fornecedor() {
		return qtd_fornecedor;
	}
	public void setQtd_fornecedor(long qtd_fornecedor) {
		this.qtd_fornecedor = qtd_fornecedor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (qtd_cliente ^ (qtd_cliente >>> 32));
		result = prime * result + (int) (qtd_fornecedor ^ (qtd_fornecedor >>> 32));
		result = prime * result + (int) (qtd_produto ^ (qtd_produto >>> 32));
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
		Home other = (Home) obj;
		if (qtd_cliente != other.qtd_cliente)
			return false;
		if (qtd_fornecedor != other.qtd_fornecedor)
			return false;
		if (qtd_produto != other.qtd_produto)
			return false;
		return true;
	}
	
}

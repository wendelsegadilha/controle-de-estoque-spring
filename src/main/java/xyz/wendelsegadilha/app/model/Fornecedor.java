package xyz.wendelsegadilha.app.model;

import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "FORNECEDORES")
public class Fornecedor extends AbstractEntity<Long>{
	
	private String nomeFantasia;
	private String razaoSocial;
	
	@OneToMany(mappedBy = "fornecedor")
	private List<Produto> produtos;
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

}

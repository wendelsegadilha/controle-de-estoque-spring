package xyz.wendelsegadilha.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "FORNECEDORES")
public class Fornecedor extends AbstractEntity<Long> implements Serializable{
	
	@NotBlank(message = "O nome fantasia é obrigatório")
	@Size(min = 3, message = "O nome fantasia deve conter no mínimo {min} caracteres.")
	@Column(name = "nome_fantasia", length = 100, nullable = false)
	private String nomeFantasia;
	
	@NotBlank(message = "A razão social é obrigatório")
	@Size(min = 3, message = "A razão social deve conter no mínimo {min} caracteres.")
	@Column(name = "razao_social", length = 100, nullable = false, unique = true)
	private String razaoSocial;
	
	@NotBlank(message = "O CNPJ é obrigatório")
	@Column(length = 20, nullable = false, unique = true)
	private String cnpj;
	
	@NotBlank(message = "A Inscrição Estadual é obrigatório")
	@Column(name = "inscricao_estadual", nullable = false, length = 20, unique = true)
	private String inscricaoEstadual;
	
	@Column(length = 100, nullable = true)
	private String email;
	
	@Column(length = 20, nullable = true)
	private String telefone;
	
	@NotBlank(message = "O celular é obrigatório")
	@Column(length = 20, nullable = false)
	private String celular;
	
	@Column(columnDefinition = "TEXT")
	private String informacoes;
	
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}

}

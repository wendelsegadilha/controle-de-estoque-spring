package xyz.wendelsegadilha.app.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENDERECOS")
public class Endereco extends AbstractEntity<Long>{
	
	@NotBlank(message = "O nome logradouro é obrigatório")
	@Size(min = 3, max = 255,  message = "O nome do logradouro deve conter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank(message = "O nome do bairro é obrigatório")
	@Size(min = 3, max = 255,  message = "O nome do birro deve conter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String bairro;
	
	@NotBlank(message = "Informe o nome da cidade")
	@Size(min = 3, max = 255,  message = "O nome da cidade deve conter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String cidade;
	
	@NotNull(message = "Selecione o um estado")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;
	
	@NotBlank(message = "O CEP é obrigatório")
	@Size(min = 9, max = 9, message = "O CEP deve conter entre {min} e {max} caracteres.")
	@Column(nullable = false, length = 9) 
	private String cep;
	
	@NotNull(message = "informe um número")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer numero;
	
	@Size(max = 255)
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}

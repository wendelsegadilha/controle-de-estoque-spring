package xyz.wendelsegadilha.app.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENTES")
public class Cliente extends AbstractEntity<Long>{
	
	@NotBlank(message = "O nome é obrigatório.")
	@Size(max = 100, min = 3, message = "O nome do cliente deve conter entre {min} e {max} caracteres.")
	@Column(length = 100, nullable = false)
	private String nome;
	
	@NotBlank(message = "O sobrenome é obrigatório.")
	@Size(max = 100, min = 3, message = "O sobrenome do cliente deve conter entre {min} e {max} caracteres.")
	@Column(length = 100, nullable = false)
	private String sobrenome;
	
	@NotBlank(message = "O CPF é obrigatório.")
	@Column(nullable = false, unique = true)
	private String cpf;
	
	@NotBlank(message = "O RG é obrigatório.")
	@Column(nullable = false, unique = true)
	private String rg;
	
	@NotNull(message = "A data de nascimento é obrigatória.")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", columnDefinition = "DATE", nullable = false)
	private LocalDate dataNascimento;
	
	@NotBlank(message = "O celular é obrigatório.")
	@Column(nullable = false)
	private String celular;
	
	@Column(nullable = true)
	private String email;
	
	@Column(columnDefinition = "TEXT", nullable = true)
	private String informacoes;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

}

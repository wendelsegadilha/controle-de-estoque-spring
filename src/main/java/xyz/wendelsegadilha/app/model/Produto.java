package xyz.wendelsegadilha.app.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUTOS")
public class Produto extends AbstractEntity<Long>{
	
	@NotBlank(message = "A descrição do produto é obrigatório")
	@Size(max = 255, min = 3, message = "A descrição do produto deve conter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String descricao;
	
	@NotNull(message = "Selecione uma categoria relativa ao produto.")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Categoria categoria;
	
	@NotBlank(message = "O nome fabricante do produto é obrigatório")
	@Size(max = 255, min = 3, message = "O nome fabricante do produto deve conter entre {min} e {max} caracteres.")
	@Column(nullable = false)
	private String fabricante;
	
	@NotNull(message = "Informe o preço de custo.")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal precoCusto;
	
	@NotNull(message = "Informe o preço de venda.")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal precoVenda;
	
	@NotNull(message = "Informe uma quantidade mínima maior que zero.")
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(columnDefinition = "TEXT")
	private String informacoes;
	
	private String foto;
	
	@NotNull(message = "Selecione um fornecedor.")
	@ManyToOne
	@JoinColumn(name = "fornecedor_id_fk")
	private Fornecedor fornecedor;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getInformacoes() {
		return informacoes;
	}
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "Produto [descricao=" + descricao + ", categoria=" + categoria + ", fabricante=" + fabricante
				+ ", precoCusto=" + precoCusto + ", precoVenda=" + precoVenda + ", quantidade=" + quantidade
				+ ", informacoes=" + informacoes + ", foto=" + foto + ", fornecedor=" + fornecedor + ", getId()="
				+ getId() + "]";
	}
	
	
}

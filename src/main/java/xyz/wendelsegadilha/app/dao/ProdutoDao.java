package xyz.wendelsegadilha.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import xyz.wendelsegadilha.app.model.Categoria;
import xyz.wendelsegadilha.app.model.Produto;
import xyz.wendelsegadilha.app.util.PaginacaoUtil;

@Repository
public class ProdutoDao extends AbstractDao<Produto, Long> {

	public PaginacaoUtil<Produto> findByName(String nome, int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Produto> produtos = getEntityManager().createQuery("select p from Produto p where p.descricao like concat('%',?1,'%')", Produto.class)
				.setParameter(1, nome)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = produtos.size();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, produtos, null);
		
	}

	public PaginacaoUtil<Produto> findByCategoria(Categoria categoria, int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Produto> produtos = getEntityManager().createQuery("select p from Produto p where p.categoria = ?1", Produto.class)
				.setParameter(1, categoria)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = produtos.size();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, produtos, null);
	}

	public  PaginacaoUtil<Produto> findByFornecedor(Long idFornecedor,int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Produto> produtos = getEntityManager().createQuery("select p from Produto p where p.fornecedor.id = ?1", Produto.class)
				.setParameter(1, idFornecedor)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = produtos.size();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, produtos, null);
		
	}
	
public PaginacaoUtil<Produto> findByReferencia(String referencia, int pagina) {
		
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Produto> produtos = getEntityManager().createQuery("select p from Produto p where p.referencia = ?1", Produto.class)
				.setParameter(1, referencia)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = produtos.size();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, produtos, null);
		
	}

	public PaginacaoUtil<Produto> buscaPaginada(int pagina) {
		int tamanho = 5;
		int inicio = (pagina - 1) * tamanho;
		List<Produto> produtos = getEntityManager().createQuery("select p from Produto p order by p.descricao asc", Produto.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, produtos, null);
	}
	
	private long count() {
		return getEntityManager().createQuery("select count(*) from Produto", Long.class).getSingleResult();
	}

}

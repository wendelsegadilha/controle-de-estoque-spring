package xyz.wendelsegadilha.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.wendelsegadilha.app.dao.ProdutoDao;
import xyz.wendelsegadilha.app.model.Categoria;
import xyz.wendelsegadilha.app.model.Produto;
import xyz.wendelsegadilha.app.util.PaginacaoUtil;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoDao dao;
	
	@Transactional(readOnly = false)
	public void salvar(Produto produto) {
		dao.save(produto);
	}
	
	@Transactional(readOnly = true)
	public List<Produto> burcarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
	}

	public Produto buscarPorId(Long id) {
		return dao.findById(id);
	}
	
	@Transactional(readOnly = false)
	public void editar(Produto produto) {
		dao.update(produto);
	}

	public PaginacaoUtil<Produto> buscarPorPagina(int pagina) {
		return dao.buscaPaginada(pagina);
	}

	public PaginacaoUtil<Produto> buscarPorPaginaNome(String nome, int pagina) {
		return dao.findByName(nome, pagina);
	}
	
	public PaginacaoUtil<Produto> buscarPorPaginaCategoria(Categoria categoria, int pagina) {
		return dao.findByCategoria(categoria, pagina);
	}
	
	public PaginacaoUtil<Produto> buscarPorPaginaFornecedor(Long idFornecedor, int pagina) {
		return dao.findByFornecedor(idFornecedor, pagina);
	}
	

}

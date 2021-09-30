package xyz.wendelsegadilha.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.wendelsegadilha.app.dao.ClienteDao;
import xyz.wendelsegadilha.app.model.Cliente;
import xyz.wendelsegadilha.app.util.PaginacaoUtil;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteDao dao;
	
	@Transactional(readOnly = false)
	public void salvar(Cliente cliente) {
		dao.save(cliente);
	}
	
	@Transactional(readOnly = false)
	public void editar(Cliente cliente) {
		dao.update(cliente);
	}
	
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public PaginacaoUtil<Cliente> buscarPorPagina(int pagina) {
		return dao.buscaPaginada(pagina);
	}
	
	@Transactional(readOnly = true)
	public PaginacaoUtil<Cliente> buscarPorPaginaNome(String nome, int pagina) {
		return dao.findByNome(nome, pagina);
	}
	
	@Transactional(readOnly = true)
	public PaginacaoUtil<Cliente> buscarPorPaginaCpf(String cpf, int pagina) {
		return dao.findByCpf(cpf, pagina);
	}
	
	@Transactional(readOnly = true)
	public Cliente buscarPorCpf(String cpf) {
		return dao.findByCpf(cpf);
	}
	
	@Transactional(readOnly = true)
	public Cliente buscarPorRg(String rg) {
		return dao.findByRg(rg);
	}

}

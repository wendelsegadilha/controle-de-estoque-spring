package xyz.wendelsegadilha.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.wendelsegadilha.app.dao.FornecedorDao;
import xyz.wendelsegadilha.app.model.Fornecedor;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorDao dao;
	
	@Transactional(readOnly = false)
	public void salvar(Fornecedor fornecedor) {
		dao.save(fornecedor);
	}
	
	@Transactional(readOnly = true)
	public List<Fornecedor> buscarTodos(){
		return dao.findAll();
	}
	
	@Transactional(readOnly = false)
	public Fornecedor buscarPorId(Long id) {
		return dao.findById(id);
	}
	
	
}

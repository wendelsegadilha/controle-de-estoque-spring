package xyz.wendelsegadilha.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.wendelsegadilha.app.dao.ClienteDao;
import xyz.wendelsegadilha.app.dao.FornecedorDao;
import xyz.wendelsegadilha.app.dao.ProdutoDao;
import xyz.wendelsegadilha.app.model.Home;

@Service
public class HomeService {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private FornecedorDao fornecedorDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	public Home getResumo() {
		Home home = new Home();
		home.setQtd_produto(produtoDao.count());
		home.setQtd_cliente(clienteDao.count());
		home.setQtd_fornecedor(fornecedorDao.count());
		return home;
	}
	
}

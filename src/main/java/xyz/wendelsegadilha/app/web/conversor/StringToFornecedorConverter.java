package xyz.wendelsegadilha.app.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import xyz.wendelsegadilha.app.model.Fornecedor;
import xyz.wendelsegadilha.app.service.FornecedorService;

@Component
public class StringToFornecedorConverter implements Converter<String, Fornecedor>{
	
	@Autowired
	private FornecedorService service;
	
	@Override
	public Fornecedor convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}

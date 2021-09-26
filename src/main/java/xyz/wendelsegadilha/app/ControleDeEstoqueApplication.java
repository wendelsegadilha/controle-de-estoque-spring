package xyz.wendelsegadilha.app;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import xyz.wendelsegadilha.app.model.Fornecedor;
import xyz.wendelsegadilha.app.service.FornecedorService;

@SpringBootApplication
public class ControleDeEstoqueApplication implements CommandLineRunner{
	
	@Autowired
	private FornecedorService fornecedorService;

	public static void main(String[] args) {
		SpringApplication.run(ControleDeEstoqueApplication.class, args);
	}
	
	@Bean
	public FixedLocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}

	@Override
	public void run(String... args) throws Exception {
		
		Fornecedor fornecedor = fornecedorService.buscarPorCnpj("94.389.491/0001-58");
		System.out.println(fornecedor);
	}

}

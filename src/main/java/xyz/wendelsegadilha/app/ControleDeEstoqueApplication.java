package xyz.wendelsegadilha.app;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class ControleDeEstoqueApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ControleDeEstoqueApplication.class, args);
		
		/**
		 * Gera senha para novos usuários manualmente
		 */
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.print("SENHA: ");
		System.out.println(encoder.encode("milton2022"));
	}
	
	@Bean
	public FixedLocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}
	
}

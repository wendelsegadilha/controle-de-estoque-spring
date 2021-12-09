package xyz.wendelsegadilha.app.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import xyz.wendelsegadilha.app.model.Home;
import xyz.wendelsegadilha.app.service.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService homeService;

	@GetMapping("/")
	public String home(ModelMap model) {
		Home home = homeService.getResumo();
		model.addAttribute(home);
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login"; // <<< Retorna a página de login
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return login(); // <<< Retorna a página de login
	}
}

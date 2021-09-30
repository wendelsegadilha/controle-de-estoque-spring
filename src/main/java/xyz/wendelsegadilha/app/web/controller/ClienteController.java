package xyz.wendelsegadilha.app.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.wendelsegadilha.app.model.Cliente;
import xyz.wendelsegadilha.app.model.UF;
import xyz.wendelsegadilha.app.service.ClienteService;
import xyz.wendelsegadilha.app.util.PaginacaoUtil;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr, ModelMap model) {

		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		Cliente clienteCpf = clienteService.buscarPorCpf(cliente.getCpf());
		if (clienteCpf != null) {
			model.addAttribute("fail", "Já existe um cliente com o CPF informado.");
			return "cliente/cadastro";
		}

		Cliente clienteRg = clienteService.buscarPorRg(cliente.getRg());
		if (clienteRg != null) {
			model.addAttribute("fail", "Já existe um cliente com o RG informado.");
			return "cliente/cadastro";
		}

		clienteService.salvar(cliente);
		attr.addFlashAttribute("success", "Cliente cadastrado com sucesso.");
		return "redirect:/clientes/cadastrar";

	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page) {

		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Cliente> pageCliente = clienteService.buscarPorPagina(paginaAtual);

		model.addAttribute("pageCliente", pageCliente);
		return "cliente/lista";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		return "cliente/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr, ModelMap model) {
		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		Cliente clienteCpf = clienteService.buscarPorCpf(cliente.getCpf());
		if (clienteCpf != null) {
			if (clienteCpf.getId() != cliente.getId()) {
				model.addAttribute("fail", "Já existe um cliente com o CPF informado.");
				return "cliente/cadastro";
			}
		}

		Cliente clienteRg = clienteService.buscarPorRg(cliente.getRg());
		if (clienteRg != null) {
			if (clienteRg.getId() != cliente.getId()) {
				model.addAttribute("fail", "Já existe um cliente com o RG informado.");
				return "cliente/cadastro";
			}
		}

		clienteService.editar(cliente);
		attr.addFlashAttribute("success", "Cliente alterado com sucesso.");
		return "redirect:/clientes/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		clienteService.excluir(id);
		attr.addFlashAttribute("success", "Cliente excluído com sucesso.");
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Cliente> pageCliente = clienteService.buscarPorPaginaNome(nome, paginaAtual);
		model.addAttribute("pageCliente", pageCliente);
		return "cliente/lista";
	}
	
	@GetMapping("/buscar/cpf")
	public String getPorCpf(@RequestParam("cpf") String cpf, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Cliente> pageCliente = clienteService.buscarPorPaginaCpf(cpf, paginaAtual);
		model.addAttribute("pageCliente", pageCliente);
		return "cliente/lista";
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

}

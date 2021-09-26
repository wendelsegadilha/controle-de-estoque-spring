package xyz.wendelsegadilha.app.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.wendelsegadilha.app.model.Fornecedor;
import xyz.wendelsegadilha.app.service.FornecedorService;
import xyz.wendelsegadilha.app.util.PaginacaoUtil;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@GetMapping("/cadastrar")
	public String cadastrar(Fornecedor fornecedor) {
		return "fornecedor/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page) {

		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Fornecedor> pageFornecedor = fornecedorService.buscarPorPagina(paginaAtual);

		model.addAttribute("pageFornecedor", pageFornecedor);
		return "fornecedor/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attr, ModelMap model) {

		// Verifica erros de validação do formulário
		if (result.hasErrors()) {
			return "fornecedor/cadastro";
		}

		Fornecedor fornCNPJ = fornecedorService.buscarPorCnpj(fornecedor.getCnpj());

		if (fornCNPJ != null) {
			model.addAttribute("fail", "Já existe um fornecedor com o CNPJ informado.");
			return "fornecedor/cadastro";
		}

		Fornecedor fornInscEst = fornecedorService.buscarPorInscricaoEstadual(fornecedor.getInscricaoEstadual());

		if (fornInscEst != null) {
			model.addAttribute("fail", "Já existe um fornecedor com Inscrição Estadual informada.");
			return "fornecedor/cadastro";
		}

		fornecedorService.salvar(fornecedor);
		attr.addFlashAttribute("success", "Fornecedor cadastrado com sucesso.");
		return "redirect:/fornecedores/cadastrar";

	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		fornecedorService.excluir(id);
		attr.addFlashAttribute("success", "Fornecedor excluído com sucesso.");
		return "redirect:/fornecedores/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fornecedor", fornecedorService.buscarPorId(id));
		return "fornecedor/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attr, ModelMap model) {

		// Verifica erros de validação do formulário
		if (result.hasErrors()) {
			return "fornecedor/cadastro";
		}

		Fornecedor fornCNPJ = fornecedorService.buscarPorCnpj(fornecedor.getCnpj());

		if (fornCNPJ != null) {
			if (fornCNPJ.getId() != fornecedor.getId()) {
				model.addAttribute("fail", "Já existe um fornecedor com o CNPJ informado.");
				return "fornecedor/cadastro";
			}
		}

		Fornecedor fornInscEst = fornecedorService.buscarPorInscricaoEstadual(fornecedor.getInscricaoEstadual());

		if (fornInscEst != null) {
			if (fornInscEst.getId() != fornecedor.getId()) {
				model.addAttribute("fail", "Já existe um fornecedor com Inscrição Estadual informada.");
				return "fornecedor/cadastro";
			}
		}

		fornecedorService.editar(fornecedor);
		attr.addFlashAttribute("success", "Fornecedor alterado com sucesso.");
		return "redirect:/fornecedores/cadastrar";
	}

	@GetMapping("/buscar/nomeFantasia")
	public String getPorNomeFantasia(@RequestParam("nomeFantasia") String nomeFantasia, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Fornecedor> pageFornecedor = fornecedorService.buscarPorPaginaNomeFantasia(nomeFantasia,
				paginaAtual);
		model.addAttribute("pageFornecedor", pageFornecedor);
		return "fornecedor/lista";
	}
	
	@GetMapping("/buscar/razaoSocial")
	public String getPorRazaoSocial(@RequestParam("razaoSocial") String razaoSocial, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Fornecedor> pageFornecedor = fornecedorService.buscarPorPaginaNomeFantasia(razaoSocial,paginaAtual);
		model.addAttribute("pageFornecedor", pageFornecedor);
		return "fornecedor/lista";
	}

}

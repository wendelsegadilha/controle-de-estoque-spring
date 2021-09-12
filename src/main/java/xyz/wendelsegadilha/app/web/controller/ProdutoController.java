package xyz.wendelsegadilha.app.web.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xyz.wendelsegadilha.app.model.Categoria;
import xyz.wendelsegadilha.app.model.Fornecedor;
import xyz.wendelsegadilha.app.model.Produto;
import xyz.wendelsegadilha.app.service.FornecedorService;
import xyz.wendelsegadilha.app.service.ProdutoService;
import xyz.wendelsegadilha.app.util.PaginacaoUtil;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://images//";
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@GetMapping("/cadastrar")
	public String cadastrar(Produto produto) {
		return "produto/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page) {
		
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Produto> pageProduto = produtoService.buscarPorPagina(paginaAtual);
		
		model.addAttribute("pageProduto", pageProduto);
		return "produto/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attr, @RequestParam("arquivo") MultipartFile arquivo) throws IOException {
		
		//Verifica erros de validação do formulário
		if (result.hasErrors()) {
			return "produto/cadastro";
		}
		
		//Upload da Foto do Produto (Não é obrigatório)
		if(!arquivo.isEmpty()) {
			//define o tipo de imagem permitido
			if (!arquivo.getContentType().equals("image/jpeg")) {
				attr.addFlashAttribute("fail", "O tipo de imagem selecionado não é permitido.");
		        return "redirect:/produtos/cadastrar";
			}
			
			String nomeImagem = uploadImagem(arquivo);
			produto.setFoto(nomeImagem);      
		} else {
			produto.setFoto("semfoto.png");
		}
		
		produtoService.salvar(produto);
		attr.addFlashAttribute("success", "Produto cadastrado com sucesso.");
		return "redirect:/produtos/cadastrar";
		
	}
	
	@GetMapping("/exibeImagem/{imagem}")
	@ResponseBody
	public byte[] getImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(UPLOADED_FOLDER + imagem);
		return Files.readAllBytes(imagemArquivo.toPath());
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		produtoService.excluir(id);
		attr.addFlashAttribute("success", "Produto excluído com sucesso.");
		return "redirect:/produtos/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produto", produtoService.buscarPorId(id));
		return "produto/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Produto produto, BindingResult result, RedirectAttributes attr, @RequestParam("arquivo") MultipartFile arquivo) {
		
		//Verifica erros de validação do formulário
		if (result.hasErrors()) {
			return "produto/cadastro";
		}
		
		//Upload da Foto do Produto (Não é obrigatório)
		if(!arquivo.isEmpty()) {
			//define o tipo de imagem permitido
			if (!arquivo.getContentType().equals("image/jpeg")) {
				attr.addFlashAttribute("fail", "O tipo de imagem selecionado não é permitido.");
		        return "redirect:/produtos/cadastrar";
			}
			String nomeImagem = uploadImagem(arquivo);
			produto.setFoto(nomeImagem);
		}
		produtoService.editar(produto);
		attr.addFlashAttribute("success", "Produto alterado com sucesso.");
		return "redirect:/produtos/cadastrar";
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Produto> pageProduto = produtoService.buscarPorPaginaNome(nome, paginaAtual);
		model.addAttribute("pageProduto", pageProduto);
		return "produto/lista";
	}
	
	@GetMapping("/buscar/categoria")
	public String getPorCategoria(@RequestParam("categoria") Categoria categoria, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Produto> pageProduto = produtoService.buscarPorPaginaCategoria(categoria, paginaAtual);
		model.addAttribute("pageProduto", pageProduto);
		return "produto/lista";
	}
	
	@GetMapping("/buscar/fornecedor")
	public String getPorFornecedor(@RequestParam("idFornecedor") Long idFornecedor, ModelMap model, @RequestParam("page") Optional<Integer> page) {
		int paginaAtual = page.orElse(1);
		PaginacaoUtil<Produto> pageProduto = produtoService.buscarPorPaginaFornecedor(idFornecedor, paginaAtual);
		model.addAttribute("pageProduto", pageProduto);
		return "produto/lista";
	}
	
	@ModelAttribute("fornecedores")
	public List<Fornecedor> getFornecedores(){
		return fornecedorService.buscarTodos();
	}
	
	@ModelAttribute("categorias")
	public Categoria[] getCategorias() {
		return Categoria.values();
	}
	
	
	private String uploadImagem(MultipartFile arquivo) {
		//Upload da Foto do Produto (Não é obrigatório)
		try {
			byte[] bytes = arquivo.getBytes();
			String novoNome = Math.random() + arquivo.getOriginalFilename();
			String caminhoComNomeArquivo = UPLOADED_FOLDER + novoNome;
			Path path = Paths.get(caminhoComNomeArquivo);
			Files.write(path, bytes);
			return novoNome;
		}catch (Exception e) {
			throw new MultipartException(e.getMessage());
		}
			
	}
	
}

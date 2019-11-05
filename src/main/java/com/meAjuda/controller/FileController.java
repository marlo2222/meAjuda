package com.meAjuda.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Curso;
import com.meAjuda.pojo.Disciplina;
import com.meAjuda.pojo.Documento;
import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.DisciplinaService;
import com.meAjuda.services.FavoritoService;
import com.meAjuda.services.FileService;
import com.meAjuda.services.UsuarioService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	@Autowired
	DisciplinaService disciplinaService;
	@Autowired
	FavoritoService favoritoService;
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/arquivo")
	public ModelAndView uploadFile(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		Curso curso =disciplinaService.cursoId(usuario.getCurso());
		Disciplina[] disciplinas = curso.getDisciplinas();
		mv.addObject("usuario", usuario.getPrimeiroNome());
		mv.addObject("disciplinas", disciplinas);
		mv.setViewName("dashboard/arquivo");
		return mv;
	}
	@PostMapping("/arquivo")
	public ModelAndView uploadFile(@RequestParam("titulo") String titulo,@RequestParam("descricao") String descricao, @RequestParam("tipo") long tipo, 
	@RequestParam("disciplina") long disciplina, @RequestParam("arquivo")MultipartFile file, HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		fileService.enviarArquivo(usuario.getId(), titulo, descricao, tipo, disciplina, file);
		favoritoService.adiconarPontos(usuario.getId(), 20);
		mv.setViewName("redirect:/home");
		return mv;
	}
	@GetMapping("/listar/Documento")
	public ModelAndView listarDocumento() {
		ModelAndView mv = new ModelAndView();
		Documento[] documentos = fileService.listarDocumentos();
		mv.addObject("documentos", documentos);
		mv.setViewName("testes/listFiles");
		return mv;
	}
	@GetMapping("/listar/documento/usuario")
	public ModelAndView listarDocumentoUsuario(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		mv.addObject("usuario", usuario.getPrimeiroNome());
		mv.addObject("documentosCompartilhados", fileService.quantidadeArquivosUsuario(usuario.getId()));
		mv.addObject("quatidadeFavoritosRecebidos", favoritoService.favoritosDocumentosUsuario(usuario.getId()));
		mv.addObject("pontuacao", favoritoService.pontuacaoUsuario(usuario.getId()));
		Documento[] documentos = fileService.listaDocumentosUsuario(usuario.getId());
		mv.addObject("documentos", documentos);
		mv.setViewName("dashboard/meusdocumentos");
		return mv;
	}
	
	@GetMapping("/listar/disciplina/{id}")
	public ModelAndView listarDocumentosDisciplina(@PathVariable("id") long idDisciplina, HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		Disciplina disciplina = disciplinaService.listarDisciplinaId(idDisciplina);
		Documento[] documentos = fileService.listaDocumentosDisciplina(idDisciplina);

		mv.addObject("usuario", usuario.getPrimeiroNome());
		mv.addObject("documentosCompartilhados", fileService.quantidadeArquivosUsuario(usuario.getId()));
		mv.addObject("quatidadeFavoritosRecebidos", favoritoService.favoritosDocumentosUsuario(usuario.getId()));
		mv.addObject("pontuacao", favoritoService.pontuacaoUsuario(usuario.getId()));
		mv.addObject("disciplina", disciplina.getNome());
		mv.addObject("documentos", documentos);
		mv.setViewName("dashboard/documentos");
		return mv;
	}
}

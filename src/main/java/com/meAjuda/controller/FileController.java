package com.meAjuda.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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
import com.meAjuda.services.DisciplinaService;
import com.meAjuda.services.FavoritoService;
import com.meAjuda.services.FileService;

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
	
	@GetMapping("/arquivo")
	public ModelAndView uploadFile(){
		long id = 3;
		Curso curso =disciplinaService.cursoId(id);
		Disciplina[] disciplinas = curso.getDisciplinas();
		ModelAndView mv = new ModelAndView();
		mv.addObject("disciplinas", disciplinas);
		mv.setViewName("dashboard/arquivo");
		return mv;
	}
	@PostMapping("/arquivo")
	public ModelAndView uploadFile(@RequestParam("titulo") String titulo,@RequestParam("descricao") String descricao, @RequestParam("tipo") long tipo, 
	@RequestParam("disciplina") long disciplina, @RequestParam("arquivo")MultipartFile file) throws IOException{
		ModelAndView mv = new ModelAndView();
		long usuario = 70;
		fileService.enviarArquivo(usuario, titulo, descricao, tipo, disciplina, file);
		favoritoService.adiconarPontos(usuario, 20);
		mv.setViewName("testes/listFiles");
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
	
	@GetMapping("/listar/disciplina/{id}")
	public ModelAndView listarDocumentosDisciplina(@PathVariable("id") long idDisciplina){
		ModelAndView mv = new ModelAndView();
		long id = 71;//no caso aqui vai ser o identificador do usuario na sessao
		mv.addObject("documentosCompartilhados", fileService.quantidadeArquivosUsuario(id));
		mv.addObject("quatidadeFavoritosRecebidos", favoritoService.favoritosDocumentosUsuario(id));
		mv.addObject("pontuacao", favoritoService.pontuacaoUsuario(id));
		Documento[] documentos = fileService.listaDocumentosDisciplina(idDisciplina);
		mv.addObject("documentos", documentos);
		mv.setViewName("dashboard/documentos");
		return mv;
	}
	
	

}

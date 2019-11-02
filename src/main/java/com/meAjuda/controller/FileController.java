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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Curso;
import com.meAjuda.pojo.Disciplina;
import com.meAjuda.pojo.Documento;
import com.meAjuda.services.DisciplinaService;
import com.meAjuda.services.FileService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping("/arquivo")
	public ModelAndView uploadFile(){
		long id = 6;
		Curso curso =disciplinaService.cursoId(id);
		Disciplina[] disciplinas = curso.getDisciplinas();
		ModelAndView mv = new ModelAndView();
		mv.addObject("disciplinas", disciplinas);
		mv.setViewName("testes/file");
		return mv;
	}
	@PostMapping("/arquivo")
	public ModelAndView uploadFile(@RequestParam("titulo") String titulo, @RequestParam("tipo") long tipo, 
	@RequestParam("disciplina") long disciplina, @RequestParam("arquivo")MultipartFile file) throws IOException{
		ModelAndView mv = new ModelAndView();
		long usuario = 1;
		fileService.enviarArquivo(usuario, titulo, tipo, disciplina, file);
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
	
	

}

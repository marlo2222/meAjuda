package com.meAjuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Curso;
import com.meAjuda.pojo.Disciplina;
import com.meAjuda.services.DisciplinaService;
import com.meAjuda.services.FavoritoService;
import com.meAjuda.services.FileService;

@Controller
public class DashboardController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	FavoritoService favoritoService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping("/home")
	public ModelAndView homeDashboard() {
		ModelAndView mv = new ModelAndView();
		long id = 71;//no caso aqui vai ser o identificador do usuario na sessao
		mv.addObject("documentosCompartilhados", fileService.quantidadeArquivosUsuario(id));
		mv.addObject("quatidadeFavoritosRecebidos", favoritoService.favoritosDocumentosUsuario(id));
		mv.addObject("pontuacao", favoritoService.pontuacaoUsuario(id));
		Curso curso = disciplinaService.cursoId(3);
	
		mv.addObject("disciplinas", curso.getDisciplinas());
		mv.setViewName("dashboard/index");
		return mv;
	}

}

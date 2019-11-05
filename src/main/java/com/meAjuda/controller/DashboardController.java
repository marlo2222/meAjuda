package com.meAjuda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Curso;
import com.meAjuda.pojo.Disciplina;
import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.DisciplinaService;
import com.meAjuda.services.FavoritoService;
import com.meAjuda.services.FileService;
import com.meAjuda.services.UsuarioService;

@Controller
public class DashboardController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	FavoritoService favoritoService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/home")
	public ModelAndView homeDashboard(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		Usuario usuario = usuarioService.usuarioAtivo(request);
		Curso curso = disciplinaService.cursoId(usuario.getCurso());
		mv.addObject("usuario", usuario.getPrimeiroNome());
		mv.addObject("documentosCompartilhados", fileService.quantidadeArquivosUsuario(usuario.getId()));
		mv.addObject("quatidadeFavoritosRecebidos", favoritoService.favoritosDocumentosUsuario(usuario.getId()));
		mv.addObject("pontuacao", favoritoService.pontuacaoUsuario(usuario.getId()));
		mv.addObject("disciplinas", curso.getDisciplinas());
		mv.setViewName("dashboard/index");
		return mv;
	}

}

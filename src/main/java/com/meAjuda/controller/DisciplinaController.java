package com.meAjuda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Curso;
import com.meAjuda.pojo.Disciplina;
import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.DisciplinaService;

@Controller
public class DisciplinaController {
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@GetMapping("/cadastro/disciplina")
	public ModelAndView cadastraDisciplina() {
		ModelAndView mv = new ModelAndView();
		//List<?> cursos = disciplinaService.listarCursos();
		mv.addObject("disciplina", new Disciplina());
		//mv.addObject("cursos", cursos);
		mv.setViewName("cadastro/cadastroDisciplina");
		//mv.setViewName("testes/curso");
		return mv;
	}
	
	@GetMapping("/cadastro/curso")
	public ModelAndView cadastraCurso() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("curso", new Curso());
		mv.setViewName("cadastro/cadastroCurso");
		//mv.setViewName("testes/curso");
		return mv;
	}
	@PostMapping("/cadastro/curso")
	public ModelAndView cadastraCurso(Curso curso, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		disciplinaService.imprimir(curso);
		disciplinaService.salvarCurso(curso,result);
		if (result.hasErrors()) {
			mv.setViewName("testes/curso"); 	
		}else {
		mv.setViewName("redirect:/login");
		}
		//mv.setViewName("cadastro/cadastroCurso");
		return mv;
	}
	
	

}

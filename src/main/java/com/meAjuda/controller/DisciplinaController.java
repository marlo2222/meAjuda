package com.meAjuda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		Curso[] cursos = disciplinaService.listarCursos();
		mv.addObject("disciplina", new Disciplina());
		mv.addObject("cursos", cursos);
		mv.setViewName("cadastro/cadastroDisciplina");
		return mv;
	}
	@PostMapping("/cadastro/disciplina")
	public ModelAndView cadastraDisciplina(Disciplina disciplina, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		disciplina.setCurso(disciplinaService.cursoId(disciplina.getIdCurso()));
		disciplinaService.salvarDisciplina(disciplina, result);
		if (result.hasErrors()) {
			mv.setViewName("cadastro/cadastroDisciplina"); 	
		}else {
		mv.setViewName("redirect:/disciplinas/listar");
		}
		return mv;
	}
	@GetMapping("/disciplinas/listar")
	public ModelAndView listarDisciplinas() {
		ModelAndView mv = new ModelAndView();
		Disciplina[] disciplinas = disciplinaService.listarDisciplinas();
		mv.addObject("disciplinas", disciplinas);
		mv.setViewName("testes/dash");
		return mv;
	}
	@GetMapping("/disciplinas/deletar/{id}")
	public ModelAndView deletarDisciplina(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView();
		disciplinaService.deletarDisciplina(id);
		mv.setViewName("redirect:/disciplinas/listar");
		return mv;
	}
	@GetMapping("/cadastro/curso")
	public ModelAndView cadastraCurso() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("curso", new Curso());
		mv.setViewName("cadastro/cadastroCurso");
		return mv;
	}
	@PostMapping("/cadastro/curso")
	public ModelAndView cadastraCurso(Curso curso, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		//disciplinaService.imprimir(curso);
		disciplinaService.salvarCurso(curso,result);
		if (result.hasErrors()) {
			mv.setViewName("cadastro/cadastroCurso"); 	
		}else {
		mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@GetMapping("/curso/deletar/{id}")
	public ModelAndView deletarCurso(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	

}

package com.meAjuda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Usuario;

@Controller
public class DisciplinaController {
	
	@GetMapping("/cadastro/disciplina")
	public ModelAndView cadastraDisciplina() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cadastro/cadastroDisciplina");
		return mv;
	}
	
	@GetMapping("/cadastro/curso")
	public ModelAndView cadastraCurso() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cadastro/cadastroCurso");
		return mv;
	}
	
	

}

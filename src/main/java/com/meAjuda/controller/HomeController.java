package com.meAjuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Curso;
import com.meAjuda.services.DisciplinaService;

@Controller
public class HomeController {
	
	@Autowired
	DisciplinaService DisciplinaService;
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		Curso[] cursos = DisciplinaService.listarCursos(); 
		mv.addObject("cursos",cursos);
		mv.setViewName("home/index");
		return mv;
	}
	

}

package com.meAjuda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;
	}
	
	@GetMapping("/registrar")
	public ModelAndView registrar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/registrar");
		return mv;
	}
}

package com.meAjuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	RestTemplate rest;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;
	}
	@PostMapping("/login")
	public ModelAndView login(@RequestBody Usuario usuario) {
		ModelAndView mv =  new ModelAndView();
		//implementar o login
		return mv;
	}
	
	@GetMapping("/registrar")
	public ModelAndView registrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/registrar");
		return mv;
	}
	@PostMapping("/registrar")
	public ModelAndView registrar(@RequestBody Usuario usuario){
		ModelAndView mv = new ModelAndView();
		//implementar o cadastro
		return mv;
	}
	
}

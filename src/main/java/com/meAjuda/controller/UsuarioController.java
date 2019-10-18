package com.meAjuda.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.UsuarioService;

@Controller
public class UsuarioController {
	

	RestTemplate rest = new RestTemplate();
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		//mv.setViewName("testes/loginTeste");
		return mv;
	}
	@PostMapping("/login")
	public ModelAndView login(@RequestBody Usuario usuario) {
		ModelAndView mv =  new ModelAndView();
		//implementar o login
		
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView registrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/cadastro");
		//mv.setViewName("testes/registrarTeste");
		return mv;
	}
	@PostMapping("/cadastro")
	public ModelAndView registrar(Usuario usuario, BindingResult result){
		
		ModelAndView mv = new ModelAndView();
		result = usuarioService.registrarUsuario(usuario, result);
		
		if (result.hasErrors()) {
			mv.setViewName("testes/registrarTeste");
			mv.addObject("usuario", usuario);
		}else {
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
}

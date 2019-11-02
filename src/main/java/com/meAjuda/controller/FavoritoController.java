package com.meAjuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.services.FavoritoService;

@Controller
public class FavoritoController {
	
	@Autowired
	FavoritoService favoritoService;
	
	@GetMapping("/favorito/adicionar/")
	public ModelAndView adicionarFavorito() {
		System.out.println("entrou aqui");
		ModelAndView mv = new ModelAndView();
		long usuario = 1;//mudar para quando usuario estiver logado
		//ResponseEntity<?> responde = favoritoService.adiconarFavorito(id, usuario);
		System.out.println("entrou aqui");
		mv.setViewName("redirect:/listar/Documento");
		return mv;
	}

}

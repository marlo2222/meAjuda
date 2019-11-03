package com.meAjuda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.services.FavoritoService;

@Controller
public class FavoritoController {
	
	@Autowired
	FavoritoService favoritoService;
	
	@GetMapping("/favorito/adicionar/")
	public ModelAndView adicionarFavorito(@RequestParam("id") long id, @RequestParam("donoDocumento") long donoDocumento) {
		
		ModelAndView mv = new ModelAndView();
		long usuario = 1;//mudar para quando usuario estiver logado
		System.out.println(id+" "+donoDocumento);
		ResponseEntity<?> response = favoritoService.adiconarFavorito(id, usuario, donoDocumento);
		System.out.println(response.getStatusCodeValue());
		if (response.getStatusCodeValue() == 201) {
			favoritoService.adiconarPontos(usuario, 40);
		}
		System.out.println("entrou aqui");
		mv.setViewName("redirect:/listar/Documento");
		return mv;
	}

}

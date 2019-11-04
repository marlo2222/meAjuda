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
	public ModelAndView adicionarFavorito(@RequestParam("id") long id, @RequestParam("donoDocumento") long donoDocumento, @RequestParam("idDisciplina") long idDisciplina) {
		
		ModelAndView mv = new ModelAndView();
		long usuario = 71;//mudar para quando usuario estiver logado
		System.out.println(usuario+""+donoDocumento);
		ResponseEntity<?> response = favoritoService.adiconarFavorito(id, usuario, donoDocumento);
		if (response.getStatusCodeValue() == 201) {
			favoritoService.adiconarPontos(donoDocumento, 40);
		}
		mv.setViewName("redirect:/listar/disciplina/"+idDisciplina);
		return mv;
	}

}

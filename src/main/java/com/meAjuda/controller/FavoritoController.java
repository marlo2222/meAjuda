package com.meAjuda.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.FavoritoService;
import com.meAjuda.services.UsuarioService;

@Controller
public class FavoritoController {
	
	@Autowired
	FavoritoService favoritoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/favorito/adicionar/")
	public ModelAndView adicionarFavorito(@RequestParam("id") long id, @RequestParam("donoDocumento") long donoDocumento,
			@RequestParam("idDisciplina") long idDisciplina, HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		ResponseEntity<?> response = favoritoService.adiconarFavorito(id, usuario.getId(), donoDocumento);
		if (response.getStatusCodeValue() == 201) {
			favoritoService.adiconarPontos(donoDocumento, 40);
		}
		mv.setViewName("redirect:/listar/disciplina/"+idDisciplina);
		return mv;
	}
	
	//problemas, como associar produtos ao favoritos
	/*@GetMapping("/favorito/listar")
	public ModelAndView listarFavoritos() {
		ModelAndView mv = new ModelAndView();
		long usuario = 71;
		
	}*/

}

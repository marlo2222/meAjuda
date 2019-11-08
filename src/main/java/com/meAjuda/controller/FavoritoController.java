package com.meAjuda.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meAjuda.pojo.Documento;
import com.meAjuda.pojo.Favorito;
import com.meAjuda.pojo.Usuario;
import com.meAjuda.services.FavoritoService;
import com.meAjuda.services.FileService;
import com.meAjuda.services.UsuarioService;

@Controller
public class FavoritoController {
	
	@Autowired
	FavoritoService favoritoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	FileService fileService;
	
	
	@GetMapping("/favorito/adicionar/")
	public ModelAndView adicionarFavorito(@RequestParam("id") long id, @RequestParam("donoDocumento") long donoDocumento,
			@RequestParam("idDisciplina") long idDisciplina, HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		if(usuario.getId() != donoDocumento) {
			ResponseEntity<?> response = favoritoService.adiconarFavorito(id, usuario.getId(), donoDocumento);
			if (response.getStatusCodeValue() == 201) {
				favoritoService.adiconarPontos(donoDocumento, 40);
			}
		}
		mv.setViewName("redirect:/listar/disciplina/"+idDisciplina);
		return mv;
	}
	
	//problemas, como associar produtos ao favoritos
	@GetMapping("/documentos/favorito/listar")
	public ModelAndView listarFavoritos(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Usuario usuario = usuarioService.usuarioAtivo(request);
		Favorito[] favoritos= favoritoService.favoritosUsuario(usuario.getId());
		Map<Object,Object> map = new HashMap<Object, Object>();
		for (Favorito favorito : favoritos) {
			map.put(favorito.getIdDocumento(), favorito.getId());
		}
		List<Documento> documentos = fileService.documentosFavoritosUsuario(favoritos, usuario.getEmail());
		mv.addObject("documentosCompartilhados", fileService.quantidadeArquivosUsuario(usuario.getId()));
		mv.addObject("quatidadeFavoritosRecebidos", favoritoService.favoritosDocumentosUsuario(usuario.getId()));
		mv.addObject("pontuacao", favoritoService.pontuacaoUsuario(usuario.getId()));
		mv.addObject("documentos", documentos);
		mv.addObject("map", map);
		mv.setViewName("dashboard/favoritos");
		return mv;
	}
	
	@GetMapping("/favorito/deletar/{id}")
	public ModelAndView deletarFavorito(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView();
		favoritoService.removerFavorito(id);
		mv.setViewName("redirect:/documentos/favorito/listar");
		return mv;
	}

}

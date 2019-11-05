package com.meAjuda.services;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.meAjuda.pojo.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	RestTemplate rest;

	private final String url = "https://passei-usuario.herokuapp.com/api/usuario";
	
	
	public BindingResult registrarUsuario(Usuario usuario, BindingResult result){
		try {
		ResponseEntity<Usuario> response = rest.getForEntity(url+"/"+usuario.getMatricula(), Usuario.class);
		
		if (response.getStatusCode().value() == 200) {
			result.rejectValue("matricula","", "Usuario com matricula já cadastrado");
			return result;}
		if (response.getStatusCode().value() == 204) {
			Usuario u = rest.postForObject(url, usuario, Usuario.class);
		}
		
		}catch (Exception e) {
			result.rejectValue("exception", "", e.getMessage());
		}
		return result;
	}
	
	public Usuario login(String matricula) {
		ResponseEntity<Usuario> response = rest.getForEntity(url+"/"+matricula, Usuario.class);
		Usuario usuario = null;
		if (response.getStatusCodeValue() == 200){
			usuario = response.getBody();
		}
		return usuario;
	}
	
	public Usuario usuarioAtivo(HttpServletRequest request) {
		String matricula = request.getUserPrincipal().getName(); 
		Usuario usuario = login(matricula);
		return usuario;
	}
}

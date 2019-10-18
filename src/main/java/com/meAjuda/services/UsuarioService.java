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
	
	RestTemplate rest = new RestTemplate();
	private final String url = "https://passei-usuario.herokuapp.com/api/usuario";
	
	
	public void imprimir(Usuario usuario) {
		System.out.println(usuario.getPrimeiroNome());
		System.out.println(usuario.getSegundoNome());
		System.out.println(usuario.getMatricula());
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getCurso());
		System.out.println(usuario.getEmail());
		
	}
	
	public BindingResult registrarUsuario(Usuario usuario, BindingResult result){
		try {
		ResponseEntity<String> response = rest.getForEntity(url+"/"+usuario.getMatricula(), String.class);
		
		if (response.getStatusCode().value() == 200 || response.getStatusCode().value() == 500)
			result.rejectValue("matricula","", "Usuario com matricula já cadastrado");
		if (!result.hasErrors()) {
			Usuario u = rest.postForObject(url, usuario, Usuario.class);
		}
		
		}catch (Exception e) {
			result.rejectValue("exception", "", e.getMessage());
		}
		return result;
	}
}

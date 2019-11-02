package com.meAjuda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

@Service
public class FavoritoService {
	
	@Autowired
	RestTemplate rest;
	
	final String urlFavorito = "https://passei-favorito.herokuapp.com/api/favorito";
	final String urlPontuacao = "https://passei-favorito.herokuapp.com/api/pontuacao/";
	
	public ResponseEntity<?> adiconarFavorito(long id, long usuario) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", id);
		body.add("usuario", usuario);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rest.postForEntity(urlFavorito, requestEntity, String.class);

		return response;
	}
	
	public Long favoritosDocumentosUsuario(long id){
		
		ResponseEntity<Long> response = rest.getForEntity(urlPontuacao+id, Long.class);
		Long quantidade = response.getBody();
		if (quantidade == null)
			quantidade = (long) 0;
		return quantidade;
		
	}
	
	public Long pontuacaoUsuario(long id){
		
		ResponseEntity<Long> response = rest.getForEntity(urlFavorito+"/listar/"+id, Long.class);
		Long quantidade = response.getBody();
		if (quantidade == null)
			quantidade = (long) 0;
		return quantidade;
		
	}
	
}

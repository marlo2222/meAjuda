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

import com.meAjuda.pojo.Favorito;

import ch.qos.logback.classic.net.SocketNode;

@Service
public class FavoritoService {
	
	@Autowired
	RestTemplate rest;
	
	final String urlFavorito = "https://passei-favorito.herokuapp.com/api/favorito/";
	final String urlPontuacao = "https://passei-favorito.herokuapp.com/api/pontuacao";
	
	public ResponseEntity<?> adiconarFavorito(long id, long usuario, long donoDocumento) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", id);
		body.add("usuario", usuario);
		body.add("donoArquivo", donoDocumento);
		System.out.println("entrou aqui");
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rest.postForEntity(urlFavorito, requestEntity, String.class);

		return response;
	}
	
	//listar os favoritos de um usuario
	public Favorito[] favoritosUsuario(long idUsuario) {
		ResponseEntity<Favorito[]> response = rest.getForEntity(urlFavorito+"usuario/"+idUsuario, Favorito[].class);
		Favorito[] favoritos = response.getBody();
		if (favoritos == null) {
			favoritos = new Favorito[0];
		}
		return favoritos;
	}
	
	//quanto favoritos os documentos do usuario receberam
	public Long favoritosDocumentosUsuario(long id){
		
		ResponseEntity<Long> response = rest.getForEntity(urlFavorito+"/listar/"+id, Long.class);
		Long quantidade = response.getBody();
		if (quantidade == null)
			quantidade = (long) 0;
		return quantidade;
		
	}
	
	//pontuação do usuario com base em um favorito
	public Long pontuacaoUsuario(long id) {
		ResponseEntity<Long> response = rest.getForEntity(urlPontuacao+"/"+id, Long.class);
		Long quantidade = response.getBody();
		if (quantidade == null)
			quantidade = (long) 0;
		return quantidade;
	}
	
	public ResponseEntity<?> adiconarPontos(long usuario, long pontos){

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("usuario", usuario);
		body.add("pontos", pontos);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		ResponseEntity<String> response = rest.postForEntity(urlPontuacao, requestEntity, String.class);

		return response;

	}	
	//remover um favorito
	public void removerFavorito(long id) {
		rest.delete(urlFavorito+"/"+id);
	}
	//remover os favoritos com base em um documento
	public void removerFavoritoDocumento(long idDocumento) {
		System.out.println("entrou");
		rest.delete(urlFavorito+"/deletar/"+idDocumento);
	}
	
	
}

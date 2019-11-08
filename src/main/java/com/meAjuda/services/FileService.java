package com.meAjuda.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.meAjuda.pojo.Documento;
import com.meAjuda.pojo.Favorito;

@Service
public class FileService {
	
	private final String urlFile = "https://passei-file.herokuapp.com/api";

	@Autowired
	RestTemplate rest;
	
	//Insere um novo arquivo
	//@CacheEvict(cacheNames = "quantidadeArquivosUsuario", allEntries = true)
	public void enviarArquivo(long idUsuario, String titulo, String descricao, long tipo, long disciplina, MultipartFile file) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("disciplina", disciplina);
		body.add("usuario", idUsuario);
		body.add("titulo", titulo);
		body.add("descricao", descricao);
		body.add("tipo", tipo);
		body.add("arquivo", getTestFile(file));

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = rest.postForEntity(urlFile+"/documento/adicionar/", requestEntity, String.class);

	}

	//@CacheEvict(cacheNames = "quantidadeArquivosUsuario", allEntries = true)
	public void deletarArquivo(long id){
		rest.delete(urlFile+"/delete/"+id);
	}
	//quantidade de arquivos usuario
	//@Cacheable(cacheNames = "quantidadeArquivosUsuario", key = "#idUsuario")
	public Long quantidadeArquivosUsuario(long idUsuario){
		ResponseEntity<Long> response = rest.getForEntity(urlFile+"/count/"+idUsuario, Long.class);
		Long quantidade = response.getBody();
		if (quantidade == null)
			quantidade = (long) 0;
		return quantidade;
	}
	
	//listar todos os documentos
	public Documento[] listarDocumentos() {
		ResponseEntity<Documento[]> response = rest.getForEntity(urlFile+"/listar", Documento[].class);
		Documento[] documentos = response.getBody();
		return documentos;
	
	}
	//listar todos os documentos de uma disciplina
	public Documento[] listaDocumentosDisciplina(long idDisciplina) {
		ResponseEntity<Documento[]> response = rest.getForEntity(urlFile+"/listar/disciplina/"+idDisciplina, Documento[].class);
		Documento[] documentos = response.getBody();
		return documentos;
	}
	//listar todos os documentos de um usuario
	public Documento[] listaDocumentosUsuario(long idusuario) {
		ResponseEntity<Documento[]> response = rest.getForEntity(urlFile+"/listar/usuario/"+idusuario, Documento[].class);
		Documento[] documentos = response.getBody();
		return documentos;
	}
	
	public ArrayList<Documento> documentosFavoritosUsuario(Favorito[] favoritos, String email){
		ArrayList<Documento> documentos = new ArrayList<Documento>();
		
		for (Favorito favorito : favoritos) {
			ResponseEntity<Documento> response = rest.getForEntity(urlFile+"/listar/documentos/favoritos/"+favorito.getIdDocumento(), Documento.class);
			Documento documento = response.getBody();
			if (documento != null) {
				documentos.add(documento);
			}
		}
		return documentos;
	}
	
	public static Resource getTestFile(MultipartFile file) throws IOException {

		File tempFile = null;
		try {
			String extension = "." + getExtensionByApacheCommonLib(file.getOriginalFilename());
			System.out.println(extension);
			tempFile = File.createTempFile(file.getName(), extension);
			file.transferTo(tempFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new FileSystemResource(tempFile.getPath());
	}

	public static String getExtensionByApacheCommonLib(String filename) {
		return FilenameUtils.getExtension(filename);
	}
	
	

}

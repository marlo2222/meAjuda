package com.meAjuda.services;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class FileService {
	
	private final String urlFile = "https://passei-file.herokuapp.com/api";

	@Autowired
	RestTemplate rest;

	public void enviarArquivo(long usuario, String titulo, String descricao, long tipo, long disciplina, MultipartFile file) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("disciplina", disciplina);
		body.add("usuario", usuario);
		body.add("titulo", titulo);
		body.add("descricao", descricao);
		body.add("tipo", tipo);
		body.add("arquivo", getTestFile(file));

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = rest.postForEntity(urlFile+"/documento/adicionar/", requestEntity, String.class);

	}
	//quantidade de arquivos usuario
	public Long quantidadeArquivosUsuario(long id){
		ResponseEntity<Long> response = rest.getForEntity(urlFile+"/count/"+id, Long.class);
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
	public Documento[] listaDocumentosDisciplina(long idDisciplina) {
		ResponseEntity<Documento[]> response = rest.getForEntity(urlFile+"/listar/disciplina/"+idDisciplina, Documento[].class);
		Documento[] documentos = response.getBody();
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

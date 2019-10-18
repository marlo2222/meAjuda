package com.meAjuda.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meAjuda.pojo.Curso;

@Service
public class DisciplinaService {
	
	private final String url = "https://passei-disciplina.herokuapp.com/api/curso/listar";
	
	RestTemplate rest = new RestTemplate();
	
	public List<?> listarCursos(){
		List<Curso> cursos = rest.getForObject(url, List.class);
		return cursos;
	}
	
	

}

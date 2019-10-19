package com.meAjuda.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.meAjuda.pojo.Curso;

@Service
public class DisciplinaService {
	
	private final String urlCurso = "https://passei-disciplina.herokuapp.com/api/curso/";
	
	RestTemplate rest = new RestTemplate();
	
	public List<?> listarCursos(){
		List<Curso> cursos = rest.getForObject(urlCurso+"listar", List.class);
		return cursos;
	}
	public BindingResult salvarCurso(Curso curso, BindingResult bindingResult){
		
		Curso newCurso = rest.postForObject(urlCurso+"salvar", curso, Curso.class);
		
		if (newCurso == null) {
			bindingResult.rejectValue("curso", "", "não foi posivel salvar o curso");
			return bindingResult;
		}
		return bindingResult;
		
	}
	
	public void imprimir(Curso curso){
		System.out.println(curso.getNome());
		System.out.println(curso.getQtdSesmestres());
	}
	
	

}

package com.meAjuda.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import com.meAjuda.pojo.Curso;
import com.meAjuda.pojo.Disciplina;

@Service
public class DisciplinaService {
	
	private final String urlCurso = "https://passei-disciplina.herokuapp.com/api/curso/";
	private final String urlDisciplina = "https://passei-disciplina.herokuapp.com/api/disciplina/";
	
	RestTemplate rest = new RestTemplate();
	
	public Curso[] listarCursos(){
		ResponseEntity<Curso[]> entity = rest.getForEntity(urlCurso+"listar", Curso[].class);
		Curso[] cursos = entity.getBody();
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
	
	public Curso cursoId(int id) {
		ResponseEntity<Curso> entity = rest.getForEntity(urlCurso+"listar/"+id, Curso.class);
		Curso curso = entity.getBody();
		return curso;
	}
	
	public BindingResult salvarDisciplina(Disciplina disciplina, BindingResult result){
		
		ResponseEntity<Disciplina> entity = rest.postForEntity(urlDisciplina+"salvar", disciplina,Disciplina.class);
		
		if (entity.getStatusCodeValue() != 201){
			result.rejectValue("Error", "", "nao foi possivel salvar a disciplina");
			return result;
		}
		return result;
	}
	
	public Disciplina[] listarDisciplinas() {
		ResponseEntity<Disciplina[]> response = rest.getForEntity(urlDisciplina+"listar", Disciplina[].class);
		Disciplina[] disciplinas = response.getBody();
		return disciplinas;
	}
	public void deletarDisciplina(long id){
		rest.delete(urlDisciplina+"deletar/"+id);
	}
	
	
	

}

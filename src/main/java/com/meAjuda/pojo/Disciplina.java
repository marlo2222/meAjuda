package com.meAjuda.pojo;

public class Disciplina {

    private long id;

    private String nome;

    private String professorAtual;

    private Curso curso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessorAtual() {
		return professorAtual;
	}

	public void setProfessorAtual(String professorAtual) {
		this.professorAtual = professorAtual;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
    
    

}

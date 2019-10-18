package com.meAjuda.pojo;

import java.util.List;


public class Curso {

    private long id;

    private String nome;

    private int qtdSesmestres;

    public List<Disciplina> disciplinas;

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

	public int getQtdSesmestres() {
		return qtdSesmestres;
	}

	public void setQtdSesmestres(int qtdSesmestres) {
		this.qtdSesmestres = qtdSesmestres;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
    
    


}

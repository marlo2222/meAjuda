package com.meAjuda.pojo;




public class Curso {

    private long id;

    private String nome;

    private int qtdSesmestres;

    private Disciplina[] disciplinas;

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

	public Disciplina[] getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Disciplina[] disciplinas) {
		this.disciplinas = disciplinas;
	}



}

package com.meAjuda.pojo;

import javax.validation.constraints.NotNull;


public class Curso {

    private long id;
    
    @NotNull(message = "esse campo é obrigatorio")
    private String nome;

    private int qtdSesmestres;
    
    @NotNull(message = "esse campo é obrigatorio")
    private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

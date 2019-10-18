package com.meAjuda.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Usuario {
	
    private long id;
    
//    @NotNull(message = "O primeiro nome 'e obrigatorio")
//    @NotEmpty(message = "O primeiro nome 'e obrigatorio")
    private String primeiroNome;
    
    private String SegundoNome;
//    @NotNull(message = "A matricula e obrigatoria")
//    @NotEmpty(message = "A matricula e obrigatoria")
    private String matricula;
    
//    @NotNull(message = "O email e obrigatorio")
//    @NotEmpty(message = "O email e obrigatorio")
    private String email;
//    
//    @NotNull(message = "A senha 'e obrigatoria")
//    @NotEmpty(message = "A senha 'e obrigatoria")
    private String senha;

    private  long curso;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSegundoNome() {
		return SegundoNome;
	}

	public void setSegundoNome(String segundoNome) {
		SegundoNome = segundoNome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getCurso() {
		return curso;
	}

	public void setCurso(long curso) {
		this.curso = curso;
	}
    
  
}

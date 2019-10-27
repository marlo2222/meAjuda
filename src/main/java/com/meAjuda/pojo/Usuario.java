package com.meAjuda.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Usuario {
	
    private long id;
   
    @NotNull(message = "O primeiro nome é obrigatorio")
    private String primeiroNome;
    
    @NotNull(message = "O primeiro nome é obrigatorio")
    private String SegundoNome;

    @NotNull(message = "A matricula e obrigatoria")
    @Length(min = 5, max = 6, message = "A matricula deve conter 6 numeros")	
    private String matricula;
    
    @NotNull(message = "O email e obrigatorio")
    @Email(message = "email precisa ser valido")
    private String email;
    
    @NotNull(message = "A senha é obrigatoria")
    @Length(min = 5, max = 100, message = "A senha deve conter entre 5 e 100 caracteres")
    private String senha;
    
    @NotNull(message = "A curso é obrigatoria")
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

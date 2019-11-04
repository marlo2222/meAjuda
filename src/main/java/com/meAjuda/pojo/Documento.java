package com.meAjuda.pojo;

import java.util.Date;

public class Documento {
	
	private long id;
	
	private String fileName;
	
	private String filetype;
	
	private long size = 0;
	
	private String fileDownloadUri;

	private String hash;

	private Date data;

	private long tipo;
	
	private String titulo;
	
	private String descricao;
	
	private long idDisciplina;
	
	


	private long idUsuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}

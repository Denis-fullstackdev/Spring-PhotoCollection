package com.corsojava.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="commentos")
public class Commento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Username obbligatorio")
	@NotNull(message="Username obbligatorio")
	@Size(min=4, max=40, message="Deve avere dimensione minimo 4 a massimo 40 caratteri.")
	private String user;
	
	@NotEmpty(message="Contenuto obbligatorio")
	@NotNull(message="Contenuto obbligatorio")
	private String content;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Foto foto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

}
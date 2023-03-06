package com.corsojava.springboot.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="fotos")
public class Foto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Titolo obbligatorio")
	@NotNull(message="Titolo obbligatorio")
	@Size(min=5, max=100, message="Deve avere dimensione minimo 5 a massimo 100 caratteri.")
	private String titolo;
	
	@NotEmpty(message="URL obbligatorio")
	@NotNull(message="URL obbligatorio")
	private String url;
	
	@NotEmpty(message="Tag obbligatorio")
	@NotNull(message="Tag obbligatorio")
	@Size(min=4, max=20, message="Deve avere dimensione minimo 4 a massimo 20 caratteri.")
	private String tag;
	
	private boolean visibile;
	
	@OneToMany(mappedBy = "foto", cascade = CascadeType.ALL)
	private List<Commento> commentos;
	
	@ManyToMany()
	private List<Categoria> categorias;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Commento> getCommentos() {
		return commentos;
	}

	public void setCommentos(List<Commento> commentos) {
		this.commentos = commentos;
	}

}

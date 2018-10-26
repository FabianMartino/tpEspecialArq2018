package com.tpEspecialArq2018;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Trabajo implements Serializable{

	private static final long serialVersionUID = -3827683517954036245L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_trabajo;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String category;
	
	@ManyToMany(mappedBy="trabajos")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@ManyToMany
	private List<Palabra> palabras;
	 
	@OneToMany(mappedBy="id_trabajo")
    private List<Evaluacion> evaluaciones;
	
	public Trabajo() {
		
	}
	
	public Trabajo(String titulo, String cat) {
		this.titulo = titulo;
		this.category = cat;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getId() {
		return id_trabajo;
	}

	public void setId(long id) {
		this.id_trabajo = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Trabajo [titulo=" + titulo + "]";
	}

	
}

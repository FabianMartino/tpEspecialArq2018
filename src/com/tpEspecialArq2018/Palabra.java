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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Palabra implements Serializable{
	
	private static final long serialVersionUID = -1304645674216415020L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id_palabra; 
	@Column(nullable = false)
	private String palabra;
	@Column(nullable = false)
	private boolean isSpecific;
	
	@ManyToMany(mappedBy ="palabras")
	@JsonIgnore
    private List<Usuario> usuarios = new ArrayList<Usuario>();
	

	@ManyToMany(mappedBy ="palabras")
	@JsonIgnore
	private List<Trabajo> trabajos = new ArrayList<Trabajo>();

	
	public Palabra() {
		
	}
	
	public Palabra(String p, boolean isSpecific) {
		palabra = p;
		this.isSpecific = isSpecific;
	}

	public long getId() {
		return id_palabra;
	}

	public void setId(int id) {
		this.id_palabra = id;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public boolean isSpecific() {
		return isSpecific;
	}

	public void setSpecific(boolean isSpecific) {
		this.isSpecific = isSpecific;
	}
	
	

}

package com.tpEspecialArq2018;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Evaluacion implements Serializable{
	
	private static final long serialVersionUID = -5786227364728994205L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	

    @ManyToOne
    @JoinColumn(name="usuario", nullable = false)
    private Usuario id_usuario;
    @ManyToOne
    @JoinColumn(name="trabajo", nullable = false)
    private Trabajo id_trabajo;
	
	public Evaluacion() {
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Trabajo getId_trabajo() {
		return id_trabajo;
	}

	public void setId_trabajo(Trabajo id_trabajo) {
		this.id_trabajo = id_trabajo;
	}
	
}

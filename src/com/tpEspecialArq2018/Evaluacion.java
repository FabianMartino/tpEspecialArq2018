package com.tpEspecialArq2018;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Evaluacion implements Serializable{
	
	private static final long serialVersionUID = -5786227364728994205L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private Date fecha;
	@Column
	private int nota;
	

    @ManyToOne
    @JoinColumn(name="usuario", nullable = false)
	@JsonIgnore
    private Usuario id_usuario;
    @ManyToOne
    @JoinColumn(name="trabajo", nullable = false)
	@JsonIgnore
    private Trabajo id_trabajo;
	
	public Evaluacion() {
	}
	public Evaluacion(Usuario idUser, Trabajo idTrabajo, Date fecha, int nota) {
		this.id_trabajo = idTrabajo;
		this.id_usuario = idUser;
		this.fecha = fecha;
		this.nota = nota;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "Evaluacion [fecha=" + fecha + ", nota=" + nota + ", id_usuario=" + id_usuario + "]";
	}
	
}

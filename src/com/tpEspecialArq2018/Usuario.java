package com.tpEspecialArq2018;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -2136060257901848874L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_user;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	
	@ManyToMany
	private List<Trabajo> trabajos;
	
	@ManyToMany
	private List<Palabra> palabras;
	
	@ManyToMany
	private List<Rol> roles;
	
	@ManyToOne
    @JoinColumn(name="lugar_id", nullable = false)
    private LugarDeTrabajo locacion;
	
	
	@OneToMany(mappedBy="id_usuario")
	private List<Evaluacion> evaluacion;

	public Usuario(){}

	public Usuario(String nombre, String apellido, LugarDeTrabajo lugar) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.locacion = lugar;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LugarDeTrabajo getLocacion() {
		return locacion;
	}

	public void setLocacion(LugarDeTrabajo locacion) {
		this.locacion = locacion;
	}

	@Override
	public String toString() {
		return "Usuario [id_user=" + id_user + ", nombre=" + nombre + ", apellido=" + apellido + ", trabajos="
				+ trabajos + ", palabras=" + palabras + ", roles=" + roles + ", locacion=" + locacion + ", evaluacion="
				+ evaluacion + "]";
	}
}

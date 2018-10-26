package com.tpEspecialArq2018;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@JoinTable(name="trabajo_usuario", 
				joinColumns = {@JoinColumn(name="id_user")}, 
				inverseJoinColumns = {@JoinColumn(name="id_trabajo")}
	)
	private List<Trabajo> trabajos  = new ArrayList<Trabajo>();
	
	@ManyToMany
	@JoinTable(name="palabra_usuario", 
				joinColumns = {@JoinColumn(name="id_user")}, 
				inverseJoinColumns = {@JoinColumn(name="id_palabra")}
	)
	private List<Palabra> palabras = new ArrayList<Palabra>();
	
	@ManyToMany
	private List<Rol> roles = new ArrayList<Rol>();
	
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
		return "Usuario [id_user=" + id_user + ", nombre=" + nombre + ", apellido=" + apellido + ", locacion=" + locacion.getName() + "]";
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public List<Trabajo> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}

	public List<Palabra> getPalabras() {
		return palabras;
	}

	public void setPalabras(List<Palabra> palabras) {
		this.palabras = palabras;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Evaluacion> getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(List<Evaluacion> evaluacion) {
		this.evaluacion = evaluacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void addTrabajo(Trabajo t){
		this.trabajos.add(t);
	}
	
	public void addRol(Rol r){
		//r.addUsuarios(this);
		this.roles.add(r);
	}
	public void addPalabra(Palabra p) {
		this.palabras.add(p);
		}
	
}

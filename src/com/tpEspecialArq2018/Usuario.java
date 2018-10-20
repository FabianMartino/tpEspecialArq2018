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
    @JoinColumn(name="lugar_id")
    private LugarDeTrabajo locacion;
	
	@OneToMany(mappedBy="id_usuario")
    private List<Evaluacion> evaluacion;

	public Usuario(){}

	public Usuario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
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
	
}

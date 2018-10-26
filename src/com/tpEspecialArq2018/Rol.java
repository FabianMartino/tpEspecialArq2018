package com.tpEspecialArq2018;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 3184024780211490593L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_rol;
	
	@Column(nullable = false)
	private String tipo;
	
	@ManyToMany(mappedBy="roles")
	List<Usuario> usuarios;
	
	public Rol() {
		
	}
	public Rol(String tipo) {
		this.tipo = tipo;
	}

	public long getId() {
		return id_rol;
	}

	public void setId(long id) {
		this.id_rol = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
//	public void addUsuarios(Usuario u) {
//		this.usuarios.add(u);
//	}
}

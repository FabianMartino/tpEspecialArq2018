package com.tpEspecialArq2018;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UsuarioDAO implements DAO<Usuario,Integer>{
	
	private static UsuarioDAO daoUsuario;
	
	private UsuarioDAO(){}

	public static UsuarioDAO getInstance() {
		if(daoUsuario==null)
			daoUsuario=new UsuarioDAO();
		return daoUsuario;
	}

	@Override
	public Usuario findById(Integer id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario=entityManager.find(Usuario.class, id);
		entityManager.close();
		return usuario;
	
	}
	
	public List<Usuario> findByEdad(int a, int b) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT p FROM Usuario p WHERE p.edad BETWEEN "+a+" AND "+b+"",Usuario.class);
		List<Usuario> result = query.getResultList();
		entityManager.close();
		return result;
	}

	@Override
	public Usuario persist(Usuario usuario) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT p FROM Usuario p",Usuario.class);
		List<Usuario> result = query.getResultList();
		entityManager.close();
		return result;
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery("DELETE FROM Usuario p WHERE p.id = "+id);  //.createQuery("DELETE FROM Perro p WHERE p.id = "+id+"",Perro.class);
		int cambios = query.executeUpdate();
		entityManager.close();
		if(cambios>0) {
			return true;	
		}else {
			return false;
		}
	}

	@Override
	public Usuario update(Integer id, Usuario entity) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Usuario> query = entityManager.createQuery("UPDATE Usuario p SET p.nombre = "+entity.getNombre()+" WHERE p.id = "+id+"",Usuario.class);
		entityManager.close();
		return entity;	
	}

}

package com.tpEspecialArq2018;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class RolDAO implements DAO<Rol,Long>{
	
	private static RolDAO daoRol;
	
	private RolDAO(){}

	public static RolDAO getInstance() {
		if(daoRol==null)
			daoRol=new RolDAO();
		return daoRol;
	}

	@Override
	public Rol findById(Long id) {
		EntityManager entityManager=EMF.createEntityManager();
		Rol rol=entityManager.find(Rol.class, id);
		entityManager.close();
		return rol;
	}
	
	@Override
	public Rol persist(Rol rol) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(rol);
		entityManager.getTransaction().commit();
		entityManager.close();
		return rol;
	}

	@Override
	public List<Rol> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Rol> query = entityManager.createQuery("SELECT p FROM Rol r",Rol.class);
		List<Rol> result = query.getResultList();
		entityManager.close();
		return result;
	}

	@Override
	public boolean delete(Long id) {
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
	public Rol update(Long id, Rol entity) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Rol> query = entityManager.createQuery("UPDATE Rol p SET p.tipo = "+entity.getTipo()+" WHERE p.id = "+id+"",Rol.class);
		entityManager.close();
		return entity;	
	}

}

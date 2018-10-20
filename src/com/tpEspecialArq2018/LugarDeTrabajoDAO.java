package com.tpEspecialArq2018;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class LugarDeTrabajoDAO implements DAO<LugarDeTrabajo,Integer>{
	
	private static LugarDeTrabajoDAO daoLugarDeTrabajo;
	
	private LugarDeTrabajoDAO(){}

	public static LugarDeTrabajoDAO getInstance() {
		if(daoLugarDeTrabajo==null)
			daoLugarDeTrabajo=new LugarDeTrabajoDAO();
		return daoLugarDeTrabajo;
	}

	@Override
	public LugarDeTrabajo findById(Integer id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		LugarDeTrabajo lugarDeTrabajo=entityManager.find(LugarDeTrabajo.class, id);
		entityManager.close();
		return lugarDeTrabajo;
	
	}
	
	public List<LugarDeTrabajo> findByEdad(int a, int b) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<LugarDeTrabajo> query = entityManager.createQuery("SELECT p FROM Usuario p WHERE p.edad BETWEEN "+a+" AND "+b+"",LugarDeTrabajo.class);
		List<LugarDeTrabajo> result = query.getResultList();
		entityManager.close();
		return result;
	}

	@Override
	public LugarDeTrabajo persist(LugarDeTrabajo usuario) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	@Override
	public List<LugarDeTrabajo> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<LugarDeTrabajo> query = entityManager.createQuery("SELECT p FROM Usuario p",LugarDeTrabajo.class);
		List<LugarDeTrabajo> result = query.getResultList();
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
	public LugarDeTrabajo update(Integer id, LugarDeTrabajo entity) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<LugarDeTrabajo> query = entityManager.createQuery("UPDATE Usuario p SET p.nombre = "+entity.getName()+" WHERE p.id = "+id+"",LugarDeTrabajo.class);
		entityManager.close();
		return entity;	
	}

}

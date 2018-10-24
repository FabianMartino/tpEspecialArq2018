package com.tpEspecialArq2018;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class TrabajoDAO implements DAO<Trabajo,Long>{
	
	private static TrabajoDAO daoTrabajo;
	
	private TrabajoDAO(){}

	public static TrabajoDAO getInstance() {
		if(daoTrabajo==null)
			daoTrabajo=new TrabajoDAO();
		return daoTrabajo;
	}
	
	@Override
	public Trabajo persist(Trabajo trabajo) {
		// TODO Auto-generated method stub
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return trabajo;
	}

	@Override
	public Trabajo findById(Long id) {
		// TODO Auto-generated method stub
		EntityManager entityManager=EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id);
		entityManager.close();
		return trabajo;
	}

	@Override
	public List<Trabajo> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Trabajo> query = entityManager.createQuery("SELECT t FROM Trabajo t",Trabajo.class);
		List<Trabajo> result = query.getResultList();
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
	public Trabajo update(Long id, Trabajo entity) {
//		EntityManager entityManager=EMF.createEntityManager();
//		TypedQuery<Trabajo> query = entityManager.createQuery("UPDATE Usuario p SET p.nombre = "+entity.getNombre()+" WHERE p.id = "+id+"",Trabajo.class);
//		entityManager.close();
		return entity;	
	}
}

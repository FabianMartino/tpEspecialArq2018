package com.tpEspecialArq2018;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EvaluacionDAO implements DAO<Evaluacion,Long>{
	
	private static EvaluacionDAO daoEvaluacion;
	
	private EvaluacionDAO(){}

	public static EvaluacionDAO getInstance() {
		if(daoEvaluacion==null)
			daoEvaluacion=new EvaluacionDAO();
		return daoEvaluacion;
	}

	@Override
	public Evaluacion findById(Long id) {
		EntityManager entityManager=EMF.createEntityManager();
		Evaluacion evaluacion=entityManager.find(Evaluacion.class, id);
		entityManager.close();
		return evaluacion;
	}
	

	@Override
	public Evaluacion persist(Evaluacion evaluacion) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(evaluacion);
		entityManager.getTransaction().commit();
		entityManager.close();
		return evaluacion;
	}

	@Override
	public List<Evaluacion> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Evaluacion> query = entityManager.createQuery("SELECT p FROM Evaluacion p",Evaluacion.class);
		List<Evaluacion> result = query.getResultList();
		entityManager.close();
		return result;
	}

	@Override
	public boolean delete(Long id) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery("DELETE FROM Evaluacion p WHERE p.id = "+id);  //.createQuery("DELETE FROM Perro p WHERE p.id = "+id+"",Perro.class);
		int cambios = query.executeUpdate();
		entityManager.close();
		if(cambios>0) {
			return true;	
		}else {
			return false;
		}
	}

	@Override
	public Evaluacion update(Long id, Evaluacion entity) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Evaluacion> query = entityManager.createQuery("UPDATE Evaluacion p SET p.usuario = "+entity.getId_usuario()+" WHERE p.id = "+id+"",Evaluacion.class);
		entityManager.close();
		return entity;	
	}

}

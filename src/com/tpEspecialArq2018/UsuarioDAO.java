package com.tpEspecialArq2018;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UsuarioDAO implements DAO<Usuario,Long>{
	
	private static UsuarioDAO daoUsuario;
	
	private UsuarioDAO(){}

	public static UsuarioDAO getInstance() {
		if(daoUsuario==null)
			daoUsuario=new UsuarioDAO();
		return daoUsuario;
	}

	@Override
	public Usuario findById(Long id) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.close();
		return usuario;
	}
	
	public String getUserData(Long id) {
		Usuario usuario = findById(id);
		return usuario.toString();
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
	public Usuario update(Long id, Usuario entity) {	
		Usuario usuario = findById(id);
		usuario.setApellido(entity.getApellido());
		usuario.setNombre(entity.getNombre());
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}
	
	public List<Trabajo> getTrabajos(Long id) {
		
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery(		
		"SELECT t "
		+ "FROM Trabajo t "
		+ "JOIN t.usuarios a "
		+ "WHERE a.id_user =:id");
		query.setParameter("id", id);
		List<Trabajo> result = query.getResultList();
		entityManager.close();
		return result;
		
	}
	public List<Trabajo> findAllTrabajosAutorRevisorPalabra(Long idAutor, Long idEvaluador, Long idPalabra) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery(		
		"SELECT t "
		+ "FROM Trabajo t "
		+ "JOIN t.usuarios a "
		+ "JOIN t.evaluaciones ev "
		+ "JOIN t.palabras pt "
		+ "WHERE a.id_user =:idAutor AND ev.id_usuario.id_user = :idEvaluador AND pt.id_palabra = :idPalabra");
		query.setParameter("idAutor", idAutor);
		query.setParameter("idEvaluador", idEvaluador);
		query.setParameter("idPalabra", idPalabra);
		 List<Trabajo> result = query.getResultList();
		entityManager.close();
		return result;
	}
	public List<Evaluacion> EvaluacionesRevisor(long id) {
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Evaluacion> query = entityManager.createQuery("SELECT e FROM Evaluacion e WHERE e.id_usuario = "+ id,Evaluacion.class);
		List<Evaluacion> result = query.getResultList();
		entityManager.close();
		return result;
	}
	public List<Trabajo> findAllTrabajosAutorRevisorFecha(Long idRevisor, String desde, String hasta) {
		EntityManager entityManager=EMF.createEntityManager();
		Query query = entityManager.createQuery(		
		"SELECT t "
		+ "FROM Trabajo t "
		+ "JOIN t.evaluaciones ev "
		+ "WHERE ev.id_usuario.id_user = :idRevisor AND ev.fecha BETWEEN '"+desde+"' AND '"+hasta+"'");
		query.setParameter("idRevisor", idRevisor);
//		query.setParameter("desde", desde);
//		query.setParameter("hasta", hasta);
		 List<Trabajo> result = query.getResultList();
		entityManager.close();
		return result;
	}
}

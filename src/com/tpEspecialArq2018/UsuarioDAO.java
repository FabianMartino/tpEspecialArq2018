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
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Usuario> query = entityManager.createQuery("UPDATE Usuario p SET p.nombre = "+entity.getNombre()+" WHERE p.id = "+id+"",Usuario.class);
		entityManager.close();
		return entity;	
	}
	
	public List<Trabajo> getTrabajos(Long id, String categoria) {
		
		EntityManager entityManager=EMF.createEntityManager();
		TypedQuery<Trabajo> query = entityManager.createQuery(
				"SELECT u FROM Usuario u WHERE u.id_user =" + id + " JOIN Trabajo t ON t.id_trabajo=u.id_user" ,Trabajo.class);
		List<Trabajo> result = query.getResultList();
		entityManager.close();
		return result;
		
	}
//	public List<Trabajo> findAllTrabajosAutorRevisorTema(int idAutor, int idEvaluador, int idTematica) {
//		EntityManager entityManager = EMF.createEntityManager();
//		Usuario autor = this.findById(idAutor);
//		Usuario evaluador = this.findById(idEvaluador);
//		
//		TematicaDAO daoT = TematicaDAO.getInstance();
//		Tematica tema = daoT .findById(idTematica);
//		if(autor != null && evaluador != null && tema !=null) {
//			Query query = entityManager.createNativeQuery(
//					"SELECT * FROM trabajo t "
//					+ "JOIN autor_trabajo aut ON t.id = aut.trabajo_id "
//					+ "JOIN evaluador_trabajo et ON t.id = et.trabajo_id "
//					+ "JOIN trabajo_tematica tt ON t.id = tt.Trabajo_id "
//					+ "WHERE aut.autor_id = :idAutor " 
//					+ "AND et.evaluador_id = :idEvaluador "
//					+ "AND tt.temas_id = :idTematica", Trabajo.class);
//			query.setParameter("idAutor", idAutor);
//			query.setParameter("idEvaluador", idEvaluador);
//			query.setParameter("idTematica", idTematica);
//			if (!query.getResultList().isEmpty()) {
//				return query.getResultList();
//			}
//		}
//		System.out.println("La consulta no devolvio ningun resultado");
//		return new ArrayList<Trabajo>();
//	}

}

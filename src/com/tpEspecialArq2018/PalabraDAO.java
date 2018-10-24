package com.tpEspecialArq2018;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PalabraDAO implements DAO<Palabra,Long>{
	
	private static PalabraDAO daoPalabra;
	
	private PalabraDAO(){}

	public static PalabraDAO getInstance() {
		if(daoPalabra==null)
			daoPalabra=new PalabraDAO();
		return daoPalabra;
	}
	
	@Override
	public Palabra persist(Palabra palabra) {
		// TODO Auto-generated method stub
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(palabra);
		entityManager.getTransaction().commit();
		entityManager.close();
		return palabra;
	}

	@Override
	public Palabra update(Long id, Palabra newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Palabra findById(Long id) {
		// TODO Auto-generated method stub
		EntityManager entityManager=EMF.createEntityManager();
		Palabra palabra=entityManager.find(Palabra.class, id);
		entityManager.close();
		return palabra;
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
	public List<Palabra> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

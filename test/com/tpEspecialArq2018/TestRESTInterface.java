package com.tpEspecialArq2018;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import org.junit.Test;

import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

public class TestRESTInterface {

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearUsuarios();
		crearPalabras();
		crearTrabajo();

	}
	
	public void crearTrabajo() throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		Trabajo t1 = new Trabajo("Trabajo1");
		t1 = TrabajoDAO.getInstance().persist(t1);
		
	}

	public void crearPalabras() throws ClientProtocolException, IOException {
			Palabra p1 = new Palabra("Palabra1", true);
			p1 = PalabraDAO.getInstance().persist(p1);
			Palabra p2 = new Palabra("Palabra2", false);
			p2 = PalabraDAO.getInstance().persist(p2);
			Palabra p3 = new Palabra("Palabra3", false);
			p3 = PalabraDAO.getInstance().persist(p3);
			Palabra p4 = new Palabra("Palabra4", true);
			p4 = PalabraDAO.getInstance().persist(p4);
			
	}

	public void crearUsuarios() throws ClientProtocolException, IOException {
		Usuario nuevo = new Usuario("juan","paez");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
		nuevo = new Usuario("sil","mu�oz");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
		nuevo = new Usuario("shaggy","vazquez");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
		nuevo = new Usuario("fabo","martino");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
    }
}

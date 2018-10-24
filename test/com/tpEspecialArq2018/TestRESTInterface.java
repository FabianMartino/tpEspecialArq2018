package com.tpEspecialArq2018;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

import utils.CSVReader;

public class TestRESTInterface {

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearLugaresDeTrabajos();
		crearUsuarios();
		crearPalabras();
		crearTrabajos();
 	}
	
	public void crearTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/trabajos.csv");
		for (String[] trabajo : trabajos) {
			Trabajo t = new Trabajo(trabajo[0]);
			t = TrabajoDAO.getInstance().persist(t);
		}
	}
	
	public void crearLugaresDeTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/lugaresDeTrabajo.csv");
		for (String[] trabajo : trabajos) {
			LugarDeTrabajo t = new LugarDeTrabajo(trabajo[0]);
			t = LugarDeTrabajoDAO.getInstance().persist(t);
		}
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
		System.out.println("palabras:"+p1.getPalabra()+", "+p2.getPalabra()+", "+p3.getPalabra()+", "+p4.getPalabra()+".");
		
	} 
	public void crearUsuarios() throws ClientProtocolException, IOException {
		
		
	}
	
	public void getUserData(Long id){
		
		
	}
	
}

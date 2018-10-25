package com.tpEspecialArq2018;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

import utils.CSVReader;

public class TestRESTInterface {
	ArrayList<LugarDeTrabajo> lugaresDeTrabajo = new ArrayList<LugarDeTrabajo>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	ArrayList<Trabajo> trabajos = new ArrayList<Trabajo>();
	ArrayList<Palabra> palabras = new ArrayList<Palabra>();
	ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
	
	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearLugaresDeTrabajos();
		crearUsuarios();
		crearPalabras();
		crearTrabajos();
		crearEvaluaciones();
		getUserData();
		trabajosAsignados();
 	}
	
	public void crearTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/trabajos.csv");
		for (String[] trabajo : trabajos) {
			Trabajo t = new Trabajo(trabajo[0]);
			this.trabajos.add(t);
			t = TrabajoDAO.getInstance().persist(t);
		}
	}
	
	public void crearLugaresDeTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/lugaresDeTrabajo.csv");
		for (String[] trabajo : trabajos) {
			LugarDeTrabajo lugar = new LugarDeTrabajo(trabajo[0]);
			lugaresDeTrabajo.add(lugar);
			lugar = LugarDeTrabajoDAO.getInstance().persist(lugar);
		}
	}	

	public void crearUsuarios() throws ClientProtocolException, IOException {
		
		ArrayList<String[]> usuarios = CSVReader.read("src/datasets/users.csv");
		int index = 0;
		for (String[] usuario : usuarios) {
			Usuario u = new Usuario(usuario[0], usuario[1], lugaresDeTrabajo.get(index++));
			this.usuarios.add(u);
			UsuarioDAO.getInstance().persist(u);
		}
	}

	public void crearEvaluaciones() throws ClientProtocolException, IOException {
		ArrayList<String[]> evaluaciones = CSVReader.read("src/datasets/evaluaciones.csv");
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		for (String[] evaluacion : evaluaciones) {
		//	Date date = new Date(sdf.parse(evaluacion[0]).getTime());
			Evaluacion e = new Evaluacion(usuarios.get(i),trabajos.get(i), new Date(),Integer.parseInt(evaluacion[1]));
			this.evaluaciones.add(e);
			e = EvaluacionDAO.getInstance().persist(e);
			i++;
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
	}
	@Test
	public void getUserData(){
		Long u = usuarios.get(0).getId_user();
		System.out.println("Test user data: "+UsuarioDAO.getInstance().getUserData(u));
	}
	@Test
	public void trabajosAsignados() {
		
	}
	
}

package com.tpEspecialArq2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

import utils.CSVReader;

public class TestRESTInterface {
	
	public final String BASE_URL="http://localhost:8080/tpEspecialArq2018/api";

	public final HttpClient client = HttpClientBuilder.create().build();
	
	ArrayList<LugarDeTrabajo> lugaresDeTrabajo = new ArrayList<LugarDeTrabajo>();
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	ArrayList<Trabajo> trabajos = new ArrayList<Trabajo>();
	ArrayList<Palabra> palabras = new ArrayList<Palabra>();
	ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
	ArrayList<Rol> roles = new ArrayList<Rol>();
	
	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException, ParseException {
		crearRoles();
		crearLugaresDeTrabajos();
		crearPalabras();
		crearTrabajos();
		crearUsuarios();
		crearEvaluaciones();
//		getTrabajosAutor();
//		getTrabajos();
//		getTrabajosAutorRevisor();
 	}
	
	public void crearTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/trabajos.csv");
		for (String[] trabajo : trabajos) {
			Trabajo t = new Trabajo(trabajo[0], trabajo[1]);
			t.addPalabra(this.palabras.get(0));
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
			u.addRol(this.roles.get(1/index));
			u.addTrabajo(this.trabajos.get(index-1));
			u.addPalabra(this.palabras.get(0));
			this.usuarios.add(u);
			UsuarioDAO.getInstance().persist(u);
		}
	}

	public void crearEvaluaciones() throws ClientProtocolException, IOException, ParseException {
		ArrayList<String[]> evaluaciones = CSVReader.read("src/datasets/evaluaciones.csv");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		for (String[] evaluacion : evaluaciones) {
			Date date = sdf.parse(evaluacion[0]);
			Evaluacion e = new Evaluacion(usuarios.get(i),trabajos.get(i), date,Integer.parseInt(evaluacion[1]));
			this.evaluaciones.add(e);
			e = EvaluacionDAO.getInstance().persist(e);
			i++;
		}
	}
	
	public void crearRoles(){
		ArrayList<String[]> rolesRead = CSVReader.read("src/datasets/roles.csv");
		for (String[] rol : rolesRead) {
			Rol r = new Rol(rol[0]);
			this.roles.add(r);
			RolDAO.getInstance().persist(r);
		}
	}
	
	public void crearPalabras() throws ClientProtocolException, IOException {
		Palabra p1 = new Palabra("Palabra1", true);
		p1 = PalabraDAO.getInstance().persist(p1);
		this.palabras.add(p1);
		Palabra p2 = new Palabra("Palabra2", false);
		p2 = PalabraDAO.getInstance().persist(p2);
		this.palabras.add(p2);
		Palabra p3 = new Palabra("Palabra3", false);
		p3 = PalabraDAO.getInstance().persist(p3);
		this.palabras.add(p3);
		Palabra p4 = new Palabra("Palabra4", true);
		p4 = PalabraDAO.getInstance().persist(p4);		
		this.palabras.add(p4);
	}
	
//	@Test
	public void getUserData(){
		System.out.println("Test user data:");
		List<Usuario> u = UsuarioDAO.getInstance().findAll();	
		System.out.println(UsuarioDAO.getInstance().getUserData(u.get(0).getId_user()));
	}
//	@Test
	public void trabajosAsignados() {
		System.out.println("trabajos asignados a un evaluador");
		List<Usuario> u = UsuarioDAO.getInstance().findAll();
		System.out.println("evaluador:" + UsuarioDAO.getInstance().getUserData(u.get(0).getId_user()));
		List<Evaluacion> e = EvaluacionDAO.getInstance().findEvaluador(u.get(0).getId_user());
		System.out.println("evaluaciones:");
		for (Evaluacion evaluacion : e) {
			System.out.println(evaluacion);
		}		
	}
//	@Test
	public void fechaEvaluados() {
		System.out.println("evaluaciones realizadas en una entre dos fechas por un autors");
		List<Usuario> u = UsuarioDAO.getInstance().findAll();
		System.out.println("evaluador:" + UsuarioDAO.getInstance().getUserData(u.get(0).getId_user()));
		List<Evaluacion> e = EvaluacionDAO.getInstance().findEntreFechas("2010-02-01", "2012-01-01", u.get(0).getId_user());
		System.out.println("evaluaciones:");
		for (Evaluacion evaluacion : e) {
			System.out.println(evaluacion.toString());
		}
	}
	
	
	public void getTrabajosAutor(){
		System.out.println("Dado un autor, retornar todos los trabajos de investigación enviados");
		Usuario u = usuarios.get(0);
		u.addTrabajo(this.trabajos.get(0));
		List<Trabajo> trabajosAutor = u.getTrabajos();
		for (Trabajo trabajo : trabajosAutor) {
			System.out.println(trabajo.toString());
		}
	}
	
	public void getTrabajos() {
		System.out.println("Consultar trabajos de investigación y sus propiedades");
		for (Trabajo trabajo : this.trabajos) {
			System.out.println(trabajo.toString());
		}
	}	
	
	
	public void getTrabajosAutorRevisor() {
		System.out.println("Seleccionar trabajos de investigación de un autor y revisor en una determinada área de investigación utilizando consultas JPQL");
		Usuario u1 = this.usuarios.get(0);
		Usuario u2 = this.usuarios.get(0);
		Palabra p = this.palabras.get(0);
		List<Trabajo> result = UsuarioDAO.getInstance().findAllTrabajosAutorRevisorPalabra(u1.getId_user(), u2.getId_user(), p.getId());
		if(!result.isEmpty()) {
			for (Trabajo trabajo : result) {
				System.out.println(trabajo);
			}
		}
		else {
			System.out.println("no se encotraron resultados");
		}
	}
	@Test
	public void getUser() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/users/30";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);
		
		
		System.out.println("\nGET "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
	@Test
	public void createUser() throws ClientProtocolException, IOException {
		String url = BASE_URL + "/users";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Roque");
		jsonObject.put("apellido", "Callejero");
		String jsonString = jsonObject.toString();

		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);
		
		
		System.out.println("\nPOST "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
	
	@Test
	public void createTrabajo() throws ClientProtocolException, IOException {
		String url = BASE_URL + "/trabajos";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("titulo", "Titulo 20");
		jsonObject.put("category", "Poster");
		String jsonString = jsonObject.toString();

		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);
		
		
		System.out.println("\nPOST "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
	@Test
	public void editUser() throws ClientProtocolException, IOException {
		String url = BASE_URL + "/users/32";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "jose");
		jsonObject.put("apellido", "cambio");
		String jsonString = jsonObject.toString();

		HttpPut put = new HttpPut(url);
		put.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(put);
		
		
		System.out.println("\nPUT "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
}

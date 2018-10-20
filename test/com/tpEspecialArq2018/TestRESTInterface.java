package com.tpEspecialArq2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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

public class TestRESTInterface {

//	public final String BASE_URL="http://localhost:8080/Practico-Jersey/api";

//	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearUsuarios();
//		getPerro();
//		listarPerros();
//		updatePerro();
//		deletePerro();
//		listarPerros();
//		findPerrosByEdad();
	}
	

	public void crearUsuarios() throws ClientProtocolException, IOException {

//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode jsonObject = mapper.createObjectNode();
//		jsonObject.put("nombre", "juan");
//		jsonObject.put("apellido", "paez");
//		String jsonString = jsonObject.toString();
		Usuario nuevo = new Usuario("juan","paez");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
		nuevo = new Usuario("sil","muñoz");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
		nuevo = new Usuario("shaggy","vazquez");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
		nuevo = new Usuario("fabo","martino");
		nuevo = UsuarioDAO.getInstance().persist(nuevo);
//		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
//		HttpResponse response = client.execute(post);
//
//		System.out.println("\nPOST "+url);
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//		String resultContent = getResultContent(response);
//		System.out.println("Response Content : " + resultContent);
//
//		jsonObject = mapper.createObjectNode();
//		jsonObject.put("nombre", "Popi");
//		jsonObject.put("raza", "Siberiano");
//		jsonObject.put("edad", 4);
//		jsonString = jsonObject.toString();
//
//		post = new HttpPost(url);
//		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
//		response = client.execute(post);
//
//		System.out.println("\nPOST "+url);
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//		resultContent = getResultContent(response);
//		System.out.println("Response Content : " + resultContent);
//		
//		jsonObject = mapper.createObjectNode();
//		jsonObject.put("nombre", "Manchita");
//		jsonObject.put("raza", "Dalmate");
//		jsonObject.put("edad", 5);
//		jsonString = jsonObject.toString();
//
//		post = new HttpPost(url);
//		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
//		response = client.execute(post);
//
//		System.out.println("\nPOST "+url);
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//		resultContent = getResultContent(response);
//		System.out.println("Response Content : " + resultContent);
//
//	}
//
//	private String getResultContent(HttpResponse response) throws IOException {
//		HttpEntity entity = response.getEntity();
//		if(entity!=null) {
//			BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
//			StringBuffer result = new StringBuffer();
//			String line = "";
//			while ((line = rd.readLine()) != null) {
//				result.append(line);
//			}
//			return result.toString();
//		}else {
//			return "";
//		}
//	}
//
//	
//	public void getPerro() throws ClientProtocolException, IOException {
//
//		String url = BASE_URL + "/perros/1";
//
//		HttpGet request = new HttpGet(url);
//
//		HttpResponse response = client.execute(request);
//		
//		System.out.println("\nGET "+url);
//
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//
//		String resultContent = getResultContent(response);
//
//		System.out.println("Response Content : " + resultContent);
//
//	}
//
//	
//	public void listarPerros() throws ClientProtocolException, IOException {
//
//		String url = BASE_URL + "/perros";
//
//		HttpGet request = new HttpGet(url);
//
//		HttpResponse response = client.execute(request);
//		
//		System.out.println("\nGET "+url);
//
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//
//		String resultContent = getResultContent(response);
//
//		System.out.println("Response Content : " + resultContent);
//
//	}
//	
//	public void updatePerro() throws ClientProtocolException, IOException {
//
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode jsonObject = mapper.createObjectNode();
//		jsonObject.put("nombre", "Roque");
//		jsonObject.put("raza", "Callejero");
//		jsonObject.put("edad", 8);
//		String jsonString = jsonObject.toString();
//
//		String url = BASE_URL + "/perros/1";
//		HttpPut request = new HttpPut(url);
//		request.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
//		HttpResponse response = client.execute(request);
//
//		System.out.println("\nPUT "+url);
//		
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//
//		String resultContent = getResultContent(response);
//
//		System.out.println("Response Content : " + resultContent);
//
//	}
//	
//	public void deletePerro() throws ClientProtocolException, IOException {
//
//		String url = BASE_URL + "/perros/2";
//		
//		HttpDelete request = new HttpDelete(url);
//		
//		HttpResponse response = client.execute(request);
//		
//		System.out.println("\nDELETE "+url);
//
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//
//		String resultContent = getResultContent(response);
//
//		System.out.println("Response Content : " + resultContent);
//
//	}
//	
//	private void findPerrosByEdad() throws ClientProtocolException, IOException {
//		
//		String url = BASE_URL + "/perros/findPerrosByEdad?from=3&to=5";
//
//		HttpGet request = new HttpGet(url);
//
//		HttpResponse response = client.execute(request);
//
//		System.out.println("\nGET "+url);
//		
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//
//		String resultContent = getResultContent(response);
//
//		System.out.println("Response Content : " + resultContent);
//		
//	}
//	
}}

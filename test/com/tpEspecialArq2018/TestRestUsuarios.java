package com.tpEspecialArq2018;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import utils.DBcreator;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.BeforeClass;

public class TestRestUsuarios {
	
	public final String BASE_URL="http://localhost:8080/tpEspecialArq2018/api";

	public final HttpClient client = HttpClientBuilder.create().build();
	
	@BeforeClass
	public static void beforeClass() throws ClientProtocolException, IOException, ParseException {
		DBcreator dbc = DBcreator.getDBcreator();
		dbc.create();
	}
	
	@After
	public void after() {
		System.out.println();
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
		
		System.out.println("createUser rest test");
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
		
		System.out.println("editUser rest test");
		System.out.println("\nPUT "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void trabajoAutor() throws ClientProtocolException, IOException {
		String url = BASE_URL + "/users/trabajos/30";
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		
		System.out.println("trabajoAutor rest test");
		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
	@Test
	public void trabajoAutorFecha()throws ClientProtocolException, IOException {

		String url = BASE_URL + "/users/evaluaciones/30/2010-02-01/2019-11-22";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);
		
		System.out.println("trabajoAutorFecha rest test");
		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
	}
	
//	@Test
	public void getUser() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/users/30";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);
		
		System.out.println("getUser rest test");
		System.out.println("\nGET "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
}

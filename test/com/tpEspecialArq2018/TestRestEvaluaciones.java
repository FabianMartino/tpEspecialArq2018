package com.tpEspecialArq2018;

import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import utils.DBcreator;

public class TestRestEvaluaciones {
	
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
	public void asignarTrabajo() throws ClientProtocolException, IOException {
		String url = BASE_URL + "/evaluaciones/asignar/30/18/2018-11-22/9";
		HttpPost post = new HttpPost(url);
		HttpResponse response = client.execute(post);
		
		System.out.println("asignarTrabajo rest test");
		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
	}	
}

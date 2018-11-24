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


public class TestRestTrabajos {
	
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
		
		System.out.println("createTrabajo rest test");
		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	}
	
}

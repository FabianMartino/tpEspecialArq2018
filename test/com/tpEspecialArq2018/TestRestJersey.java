//package com.tpEspecialArq2018;
//
//import org.junit.Test;
//
//import org.testng.Assert;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//public class TestRestJersey {
//
//	 
//	public final String BASE_URL="http://localhost:8080/tpEspecialArq2018/api";
//
//	public Client client = Client.create();
//
//	@Test
//	public void testGetUser() {
//
//		String url = BASE_URL + "/perros/1";
//		WebResource webResource = client.resource(url);
//		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
//
//		System.out.println("\nGET "+url);
//		System.out.println("Response Code : " + response.getStatus());
//		System.out.println("Response Content : " + response.getEntity(String.class));
//		Assert.assertEquals(response.getStatus(), 200);
//		
//	}
//}
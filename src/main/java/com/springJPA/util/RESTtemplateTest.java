package com.springJPA.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.web.client.RestTemplate;

public class RESTtemplateTest {

	
	
	  public static void main(String[] args) throws IOException,InterruptedException {
	// Create an instance of HttpClient
	  HttpClient httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL) .build(); 
	  //HttpClient
	  
	  httpClient = HttpClient.newHttpClient();
	  
	  // Define the URL of the API endpoint
	  String url ="https://countriesnow.space/api/v0.1/countries/capital";
	  
	  
	 
	  // Define the JSON payload 
	  String jsonPayload = "{\"country\": \"india\"}";
	  	
	  // Create an HttpRequest object with POST method and JSON payload 
	  HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
	  .header("Content-Type", "application/json")
	  .POST(HttpRequest.BodyPublishers.ofString(jsonPayload)).build();
	  
	  // Send the request and retrieve the response 
	  HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	  
	  // Check if the request was successful (status code 200) 
		if (response.statusCode() == 200) { // Print the response body
			System.out.println("Response from server:");
			System.out.println(response.body());
		} else { 
			// If the request was notsuccessful, print an error message
			System.out.println("Error: " + response.statusCode());
		}
		
		
		RestTemplate restTemplate = new RestTemplate();
		//String response = restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", new Object(), String.class);
		String res=restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/3", String.class);
		System.out.println(res);
	}
	 
	
	
}

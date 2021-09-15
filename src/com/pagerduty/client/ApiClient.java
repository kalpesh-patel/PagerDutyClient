package com.pagerduty.client;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.Exception;
import java.lang.InterruptedException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URLEncoder;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * ApiClient is used to retrieve results from the PD API.
 * 
 */
public class ApiClient {
	protected String token = "y_NbAkKc66ryYTWUXYEu";	// default token
	
	public ApiClient() {
	}

	public ApiClient(String token) {
		this.token = token;
	}

	/**
	 * 
	 * @param query The query to send to the PD API
	 * @param offset The offset value for the query
	 * @param limit The number of records to return
	 * @return UserListResponse The full body of the response as a UserListResponse class
	 * @throws Exception, InterruptedException, IOException, UnsupportedEncodingException, URISyntaxException 
	 * The calling function will need to handle all exceptions
	 */
	public UserListResponse getUsers(String query, int offset, int limit) 
			throws Exception, InterruptedException, IOException, UnsupportedEncodingException, URISyntaxException {
		// Build the request to send to the PD API
		HttpRequest request = HttpRequest.newBuilder()
				  .uri(new URI("https://api.pagerduty.com/users/?" +
						  "query=" + URLEncoder.encode(query, StandardCharsets.UTF_8.toString()) + 
						  "&offset=" + offset + 
						  "&limit=" + limit + 
						  "&include%5B%5D=contact_methods"))
				  .header("Authorization", "Token token=" + this.token)
				  .GET()
				  .build();

		// Retrieve a response from the server
		HttpResponse<String> response = HttpClient.newBuilder()
				  .build()
				  .send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() > 200)
			throw new Exception("The server returned an unexpected " + response.statusCode() + " response.");

		Gson gson = new Gson();
		return gson.fromJson(response.body(), UserListResponse.class);		
	}
}
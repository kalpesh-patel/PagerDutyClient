package com.pagerduty.client;

/**
 * A simple controller to run tests against the ApiClient.
 * 
 */public class Tester {
	public void executeAll() {
		int successes = 0;
		int failures = 0;

		try {
			ApiClient api = new ApiClient();
			UserListResponse response;
			
			// Test getting all users
			response = api.getUsers("", 0, 100);
			if (response.users != null && response.users.size() == 47) {
				System.out.println("Success: Getting all users.");
				successes++;
			} else { 
				System.out.println("	FAILURE: Getting all users!");
				failures++;
			}
			
			// Test sending special characters in query
			response = api.getUsers("\\\29", 0, 10);
			if (response.users != null && response.users.size() == 0) {
				System.out.println("Success: Sending special characters in query.");
				successes++;
			} else { 
				System.out.println("	FAILURE: Sending special characters in query!");
				failures++;
			}
			
			// Test with known value
			response = api.getUsers("ace", 0, 10);
			if (response.users != null && response.users.size() == 1) {
				System.out.println("Success: With known value.");
				successes++;
			} else { 
				System.out.println("	FAILURE: With known value!");
				failures++;
			}
			
			// Test more value
			response = api.getUsers("", 0, 10);
			if (response.more)  {
				System.out.println("Success: Testing more value.");
				successes++;
			} else { 
				System.out.println("	FAILURE: Testing more value!");
				failures++;
			}
			
			System.out.println("Success Count:" + successes);
			System.out.println("Failure Count:" + failures);
		} catch (Exception e) {
			System.out.println("ERROR:  " + e.toString());
		}
	}
}

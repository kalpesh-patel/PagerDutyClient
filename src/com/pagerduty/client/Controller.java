package com.pagerduty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Exception;
import java.lang.NumberFormatException;
import java.util.ArrayList;

/**
 * The Controller is the main class for the PagerDutyClient utility.  
 * It provides a user interface to interact with the PD API and 
 * allows users to search and display records from the address book.
 * 
 */
public class Controller {
	public static final int PAGE_SIZE = 10;	// The page size to display
	
	public static void main(String[] args) {
		// Tester t = new Tester();
		// t.executeAll();
		Controller c = new Controller();
		c.menu();
	}
	
	/**
	 * The primary function to manage user input and display results.
	 */
	protected void menu() {
		// Display the initial instructions
		System.out.println("Welcome to the PagerDuty Client!");
		System.out.println("This utility allows you to search and display records from the address book.\n");
		this.help();
		
		// Keep looping until user quits the application
		String cmd = "";	// The command entered by the user
		String search = "";	// The current or last search entered by the user to query the API.
		UserListResponse response = null;	// A variable to store the response from a call to ApiClient.getUsers
		ArrayList<User> users = null;	// The current list of users retrieved from the API.
		while (!cmd.equalsIgnoreCase("q")) {
			// Prompt the user for a command
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(search + "> ");
			try {
				cmd = in.readLine();
			} catch (IOException e) {
				// There was an error reading the command from the command line.
				System.out.println("Sorry, please try again.");
				continue;
			}
			
			// Check if the user entered a number (to view a specific record)
			try {
				int i = Integer.parseInt(cmd);
				
				if (i > 0 && users != null && i <= users.size()) {
					// User entered a valid index, display the record and wait for another command
					System.out.println(users.get(i-1));
					continue;
				} else {
					// User entered an invalid index
					System.out.println("Sorry, that value is incorrect.");
					continue;
				}
			} catch (NumberFormatException e) {
				// User didn't enter a number at all.  This could still be valid.
				// Continue trying to the next block of code.
			}

			// The user entered something besides a number
			switch (cmd) {
				case "h": case "H":
					// Display the help screen
					this.help();
					break;
				case "q": case "Q":
					// Exit the loop (and the application)
					break;
				default:
					// The user wants to either query the API or get the next page of results.
					int oldSize = 0;	// A temporary variable to store the size of the users ArrayList.
					if (!cmd.equalsIgnoreCase("n")) {
						// The user wants to query the API
						search = cmd;	// save the search value
						oldSize = 0;	// the current user list is empty
						users = new ArrayList<User>();	// reset the users ArrayList
					} else {
						// The user wants to get the next page of results.
						if (users == null || response == null || !response.more) {
							System.out.println("There are no additional results.");
							continue;
						}
						oldSize = users.size();
					}
					
					// Go ahead and query the PD API
					try {
						ApiClient api = new ApiClient();
						// Call the PD API using the last search entered by the user
						// and the current size of the users ArrayList as the offset
						response = api.getUsers(search, oldSize, Controller.PAGE_SIZE);
						if (response.users != null && response.users.size() > 0) {
							// Combine the retrieved results with the current list of users
							users.addAll(response.users);
							for (int i=oldSize; i < users.size(); i++) {
								// Display a list of users including the index and user name
								User user = users.get(i);
								System.out.print(i+1);
								System.out.println("\t" + user.name);
							}
						} else
							System.out.println("There are no results.");
					} catch (Exception e) {
						/*
						   We could handle each exception thrown by the ApiClient separately,
						   but the end result is the same.  We display the error message to the 
						   user in case it's useful.  Alternatively we could log it and display
						   a more user friendly error message.
						*/
						System.out.println("Sorry, we encountered an issue while processing your request.");
						System.out.println("Here's the error in case it's useful:  " + e.toString());
					}
			}
		}
	}
	
	/**
	 * A function to display instructions to the user.
	 * 
	 */
	protected void help() {
		System.out.println("You can enter the following values at the prompt:\n" +
				"QUERYING:\n" + 
				"- Type the query you would like to use to search the address book\n" + 
				"- Press <enter> to display all results\n" +
				"NAVIGATING:\n" + 
				"- Enter the number of the contact you would like to view from the search results\n" +
				"- Enter 'n' to display the next page of results\n" +
				"GENERAL:\n" + 
				"- Enter 'h' to display this help message\n" +
				"- Enter 'q' to quit the application\n");
	}

}
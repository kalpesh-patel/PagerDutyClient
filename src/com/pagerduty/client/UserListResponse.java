package com.pagerduty.client;

import java.util.ArrayList;

/**
 * UserListResponse is used to store a response when calling the users endpoint of the PD API.
 *
 */
public class UserListResponse {
	public ArrayList<User> users;
	public int offset;
	public int limit;
	public boolean more;
	public int total;
}

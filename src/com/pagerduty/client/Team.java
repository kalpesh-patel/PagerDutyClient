package com.pagerduty.client;

/**
 * A team is a collection of Users and Escalation Policies that 
 * represent a group of people within an organization.
 *
 */
public class Team {
	public String id;
	public String summary;

	public String toString() {
		if (this.summary != null && !this.summary.isBlank())
			return "Team: " + this.summary + "\n";
		
		return "";
	}
}

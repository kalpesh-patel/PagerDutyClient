package com.pagerduty.client;

/**
 * Users are members of a PagerDuty account that have the ability 
 * to interact with Incidents and other data on the account.
 *
 */

public class User {
	public String id;
	public String summary;
	public String name;
	public String email;
	public String time_zone;
	public String description;
	public String job_title;
	public Team[] teams;
	public ContactMethod[] contact_methods;
	public NotificationRule[] notification_rules;
		
	public String toString() {
		String retval = "";
		
		retval += this.name + "\n";
		if (this.job_title != null && !this.job_title.isBlank())
			retval += "Title: " + this.job_title + "\n";
		if (this.time_zone != null && !this.time_zone.isBlank())
			retval += "Time Zone: " + this.time_zone + "\n";
		for (Team team: this.teams)
	         retval += team;

		for (ContactMethod contact: this.contact_methods)
	         retval += contact;

		for (NotificationRule rule: this.notification_rules)
	         retval += rule;

		return retval;
	}
}

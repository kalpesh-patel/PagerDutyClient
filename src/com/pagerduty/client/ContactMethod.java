package com.pagerduty.client;

/**
 * Contact method for a user.
 *
 */
public class ContactMethod {
	public String id;
	public String type;
	public String summary;
	public String label;
	public String address;

	public String toString() {
		String retval = "";
		
		switch (this.type) {
	        case "email_contact_method":
	        	retval += "Email";
	            break;
	        case "sms_contact_method":
				retval += "SMS";
	            break;
	        default:
				retval += "Phone";
		}
		retval += " (" + this.label + "): ";

		// Format address as a phone number if it is
		if (this.address != null && this.address.matches("^[0-9]{10}$")) {
			retval += "(" + this.address.substring(0,3) + ")"
			+ this.address.substring(3,6) + "-"
			+ this.address.substring(6) + "\n";
		} else
			retval += this.address + "\n";
		return retval;
	}
}

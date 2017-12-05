package edu.txstate.internet;

import edu.txstate.internet.cyberflix.data.customer.Customer;

public class UserManager {
	private Customer customer;
	private static UserManager instance = null;

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	private UserManager () {
	customer = new Customer();
	}
	
	public Customer getUser() {
		return customer;
	}
	
	public void logInAs(Customer customer) {
		this.customer = customer;
	}
}

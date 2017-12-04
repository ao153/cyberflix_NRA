package edu.txstate.internet.cyberflix.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.internet.cyberflix.data.customer.Customer;

public class CustomerDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());

	public Customer findCustomerByEmail(String emailAddress) {
		final String CUSTOMER_SELECT = "SELECT * FROM customer ";
		final String EMAIL_CLAUSE = "WHERE email = ";
		final String selectString = CUSTOMER_SELECT + EMAIL_CLAUSE + '"' + emailAddress + '"'; 
		
		List<Customer> customers = new ArrayList<>();
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			while (results.next()) {
				customers.add(new Customer(
						results.getInt("customer_id"),
						results.getString("first_name"),
						results.getString("last_name"),
						results.getString("email"),
						results.getString("password")
				));
			}
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("CustomerDAO.findByEmail: " + e.toString());
			LOGGER.severe(e.toString());
			DAO.closeQuietly(dbConnection);
		}
		if (customers.isEmpty())
			return null;
		else	
			return customers.get(0);
	}

	@Override
	public void save(Object anObject) throws SQLException {
		// TODO Auto-generated method stub
	}

}

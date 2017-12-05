package edu.txstate.internet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.customer.Customer;

/**
 * Servlet implementation class CyberFlixLoginServlet
 */
@WebServlet("/CyberFlixLoginServlet")
public class CyberFlixLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email_address");
		String password = request.getParameter("password");
		System.out.println(email);
		
		Customer customer = DataSource.findCustomerByEmail(email);
		if (customer == null) {
			response.getWriter().append("Customer not found. "
					+ "Please close this window and try again.");
			System.out.println("customer not found");
		} else {
			if (password.equals(customer.getPassword())) {
				response.getWriter().append("Logged in as " 
						+ customer.getFirstName() + " " 
						+ customer.getLastName() 
						+ ". You may close this window.");
				System.out.println("logged in as " + customer.getFirstName());	
			} else {
				response.getWriter().append("Invalid Password. Close this window to attempt to login again.");
				System.out.println("invalid password");
			}
			
		}
		//doGet(request, response);
		
	}

}

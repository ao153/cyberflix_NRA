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
		System.out.println(email);
		
		Customer customer = DataSource.findCustomerByEmail(email);
		if (customer == null)
			System.out.println("customer not found");
		else
			System.out.println("logged in as " + customer.getFirstName());
		
		//doGet(request, response);
		
	}

}

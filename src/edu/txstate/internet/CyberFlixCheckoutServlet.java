package edu.txstate.internet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.customer.RentalRecord;

/**
 * Servlet implementation class CyberFlixCheckoutServlet
 */
@WebServlet("/CyberFlixCheckoutServlet")
public class CyberFlixCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixCheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filmCount = request.getParameter("film_count");
		String firstName = DataSource.getUser().getFirstName();
		String customerName = DataSource.getUser().getFirstName()
			+ " " + DataSource.getUser().getLastName();
		String customerEmail = DataSource.getUser().getEmailAddress();
		
		request.setAttribute("film_count", filmCount);
		request.setAttribute("first_name", firstName);
		request.setAttribute("customer_name", customerName);
		request.setAttribute("customer_email", customerEmail);
		
		// DEBUGGING RENTALRECORDS HERE
		
		//RentalRecord myRecord = new RentalRecord(0, null, 0, 0, null);
		
		// END RENTALRECORDS DEBUGGING
		
		// forward this request to the following jsp page
		request.getRequestDispatcher("checkout.jsp").
		    forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

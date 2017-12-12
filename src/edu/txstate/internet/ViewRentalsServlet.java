package edu.txstate.internet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.customer.Customer;
import edu.txstate.internet.cyberflix.data.customer.RentalRecord;
import edu.txstate.internet.cyberflix.data.film.Film;

/**
 * Servlet implementation class CyberFlixCartServlet
 */
@WebServlet("/ViewRentalsServlet")
public class ViewRentalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRentalsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		HttpSession session = request.getSession();
		ArrayList<RentalRecord> currentRentals = (ArrayList<RentalRecord>) DataSource.findCheckedOutRentals();
		
		ArrayList<Film> rentalFilms = new ArrayList<>();
		ArrayList<String> rentalUsers = new ArrayList<>();
		HashMap<String, String> rentalMap = new HashMap<>();
		HashMap<String, String> dateMap = new HashMap<>();
		
		for (RentalRecord rental : currentRentals) {
			int filmID = rental.getFilmID();
			String filmTitle = DataSource.findFilmByID(filmID).getTitle();
			int userID = rental.getCustomerID();
			Customer user = DataSource.findCustomerByID(userID);
			String userName = user.getFirstName() + " " + user.getLastName();
			rentalMap.put(filmTitle, userName);
			dateMap.put(filmTitle, rental.getRentalDate().toString());
			
			//System.out.println("filmID - " + filmID + " userID - " + DataSource.findCustomerByID(userID).getFirstName() );
			rentalFilms.add(DataSource.findFilmByID(filmID));
			rentalUsers.add(userName);
		}
		
		// pass the list of rentals
		request.setAttribute("films", rentalFilms);
		request.setAttribute("users", rentalUsers);
		request.setAttribute("rentalMap", rentalMap);
		request.setAttribute("dateMap", dateMap);

		// forward this request to the following jsp page
		request.getRequestDispatcher("viewrentals.jsp").
		    forward(request,  response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
	}

}

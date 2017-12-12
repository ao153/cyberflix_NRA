package edu.txstate.internet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		Customer myUser = DataSource.getUser();
		String firstName = myUser.getFirstName();
		String customerName = myUser.getFirstName()
			+ " " + myUser.getLastName();
		String customerEmail = myUser.getEmailAddress();
		
		request.setAttribute("film_count", filmCount);
		request.setAttribute("first_name", firstName);
		request.setAttribute("customer_name", customerName);
		request.setAttribute("customer_email", customerEmail);
		
		/* BEGIN CURRENT RENTAL CODE */
		ArrayList<RentalRecord> currentRentals = (ArrayList<RentalRecord>) DataSource.findRentalByCustomer(myUser);
		ArrayList<Film> rentalFilms = new ArrayList<>();
		HashMap<String, String> dateMap = new HashMap<>();
		for (RentalRecord rental : currentRentals) {
			int filmID = rental.getFilmID();
			String filmTitle = DataSource.findFilmByID(filmID).getTitle();
			dateMap.put(filmTitle, rental.getRentalDate().toString());
			rentalFilms.add(DataSource.findFilmByID(filmID));
		}
		request.setAttribute("films", rentalFilms);
		request.setAttribute("dateMap", dateMap);
		/* END CURRENT RENTAL CODE */
		
		// forward this request to the following jsp page
		request.getRequestDispatcher("checkout.jsp").
		    forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		HttpSession session = request.getSession();
		
		Customer myUser = DataSource.getUser();
		Cart myCart = DataSource.getCart(session);
		
		for (Film film : myCart.getCartFilms()) {
			int userID = myUser.getId();
			RentalRecord newRecord = new RentalRecord(0, null, film.getFilmID(), userID, null);	
			DataSource.saveNewRental(newRecord);
			System.out.println("CHECKED OUT - FILM ID: " + newRecord.getFilmID());
		}
		
		ArrayList<RentalRecord> myRentals = (ArrayList<RentalRecord>) DataSource.findRentalByCustomer(myUser);
		System.out.println("Currently checked out to you...");
		for (RentalRecord record : myRentals) {
			Film rentedFilm = DataSource.findFilmByID(record.getFilmID());
			Customer rentedFilmUser = DataSource.findCustomerByID(record.getCustomerID());
			System.out.println("FILM: " + rentedFilm.getTitle() + " - USER: " + rentedFilmUser.getFirstName());	
		}
	}
}

package edu.txstate.internet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.customer.RentalRecord;
import edu.txstate.internet.cyberflix.data.film.Film;

/**
 * Servlet implementation class CyberFlixCartServlet
 */
@WebServlet("/CyberFlixCartServlet")
public class CyberFlixCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		HttpSession session = request.getSession();
		Cart myCart = DataSource.getCart(session);
		
		/*
		for (Film film : myCart.getCartFilms()) {
			int userID = DataSource.getUser().getId();
			RentalRecord newRecord = new RentalRecord(0, null, film.getFilmID(), userID, null);	
			DataSource.saveNewRental(newRecord);
		}
		*/
		
		// pass the list of films that matched the search query
		request.setAttribute("films", myCart.getCartFilms());

		// forward this request to the following jsp page
		request.getRequestDispatcher("shoppingcart.jsp").
		    forward(request,  response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
	}

}

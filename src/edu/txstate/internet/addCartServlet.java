package edu.txstate.internet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.film.Film;

/**
 * Servlet implementation class addCartServlet
 */
@WebServlet("/addCartServlet")
public class addCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		String myFilmTitle = request.getParameter("addFilm");
		System.out.println("get method yo - we should add " + myFilmTitle);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		Film myFilm = (Film) DataSource.findFilmByTitle(
			request.getParameter("addFilm"));
		
		String sessionID = request.getSession().getId();
		Cart myCart = DataSource.getCart(sessionID);
		
		myCart.addFilm(myFilm);
		
		System.out.println("films in cart...");
		for (Film film : myCart.getCartFilms()) {
			System.out.println(film.getTitle());
		}
	}

}

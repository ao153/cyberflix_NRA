package edu.txstate.internet;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import edu.txstate.internet.cyberflix.utils.ServletUtils;
import edu.txstate.internet.cyberflix.data.db.FilmDAO;

/**
 * Servlet implementation class CyberFlixServlet
 */
@WebServlet("/CyberFlixServlet")
public class CyberFlixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		ServletConfig myConfig = getServletConfig();
		ServletUtils.setAbsolutePath(myConfig);
		DataSource.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Film> foundFilms = null;
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		HashMap<String, String> myHash = new HashMap<>();
		for (String param : parameterNames) {
			String key = param;
			String value = request.getParameter(param);
			if (value.equals("Any")) value = "0";
			System.out.println(key + ": " + value);
			myHash.put(key, value);
		}

		if (myHash.containsKey("category")) {
			// catch category selection
			foundFilms = DataSource.findFilmsByCategory(
				FilmCategory.valueOf(myHash.get("category")));

		} else if (myHash.containsKey("alpha")) {
			// catch alphabetical link choice
			foundFilms = DataSource.findFilmsAlphabetically(myHash.get("alpha"));
		} else {
			// make the correct SQL Query based on user input
			foundFilms = DataSource.findFilmsByAttributes(
				myHash.get("film_title"), myHash.get("film_description"), 
				new Integer(myHash.get("length")), 
				FilmRating.valueOf(myHash.get("film_rating")));
			
			// we can add something that detects if all fields are blank to avoid a blank/weird SQL query..
			// probably low on the priority list compared to some other stuff
			// most easily done with javascript in a <script> tag on the page itself7
		}
		
		// HERE IS THE CART CODE 
		String sessionID = request.getSession().getId();
		DataSource.createCartAt(sessionID);
		//session.setAttribute("userCart", new Cart());
		
		// pass the path of the detail servlet that will be encoded in the hyperlink for
		// associated with the film’s title
		request.setAttribute("detailServlet",   
		   "http://localhost:8080/CyberFlix_NRA/CyberFlixMovieDetailServlet");

		// pass the list of films that matched the search query
		request.setAttribute("films", foundFilms);

		// forward this request to the following jsp page
		request.getRequestDispatcher("moviesearchresults.jsp").
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

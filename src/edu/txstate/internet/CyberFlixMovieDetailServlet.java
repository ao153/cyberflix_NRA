package edu.txstate.internet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.utils.ServletUtils;

/**
 * Servlet implementation class CyberFlixMovieDetailServlet
 */
@WebServlet("/CyberFlixMovieDetailServlet")
public class CyberFlixMovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixMovieDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		HashMap<String, String> myHash = new HashMap<>();
		for (String param : parameterNames) {
			String key = param;
			String value = request.getParameter(param);
			System.out.println(key + ": " + value);
			myHash.put(key, value);
		}

		String image = myHash.get("source");
		String filmTitle = myHash.get("film_title");
		Film myFilm = DataSource.findFilmByTitle(filmTitle);
		Film filmDetail = DataSource.getFilmDetail(myFilm); 
		
		// pass the film that matched the search query
		request.setAttribute("film", filmDetail);
		request.setAttribute("image", image);
		
		// forward this request to the following jsp page
		request.getRequestDispatcher("moviedetail.jsp").
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

package edu.txstate.internet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.film.Film;
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
		List <Film> foundFilms = DataSource.findFilmByTitle(request.getParameter("film_title"));
		
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

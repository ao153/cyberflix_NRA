package edu.txstate.internet.cyberflix.data;

import java.util.List;

import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.customer.Customer;
import edu.txstate.internet.cyberflix.data.db.CustomerDAO;
import edu.txstate.internet.cyberflix.data.db.FilmDAO;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;

public class DataSource {
	/*
	final static String FILM_FILE             = "films.csv";
	final static String ACTORS_FILE           = "actors.csv";
	final static String FILM_ACTORS_LINK_FILE = "film_actors.csv";
	*/
	
	public static void init () {
		/*
		String realPath = ServletUtils.getProjectInputFilesPath();
		FilmReader aReader = new FilmReader ();
		List <Film> films   = aReader.readFilmFile(realPath, FILM_FILE);
        FilmCatalog filmInventory = FilmCatalog.getInstance();
        filmInventory.addAll(films);
        
		ActorReader actorReader = new ActorReader ();
		List <Actor> actors = actorReader.readActorFile(realPath, ACTORS_FILE);
		ActorInventory actorInventory = ActorInventory.getInstance();
		actorInventory.addAll(actors);
		
		FilmActorReader filmActorReader = new FilmActorReader ();
		List <SimpleEntry <Integer, Integer>> pairs = filmActorReader.readFilmActorFile(realPath, FILM_ACTORS_LINK_FILE);
		
		FilmActorBuilder builder = new FilmActorBuilder ();
		builder.build(filmInventory, actorInventory, pairs);
		*/
	}
	
	public static List<Film> findFilmByTitle (String title) {
		return new FilmDAO().findFilmsByAttributes(title, null, 0, null);
		//return findFilmByStrategy(new StrategyFindFilmByTitle(title));
	}

	public static List<Film> findNewestFilms(int maxNew) {
		return new FilmDAO().findNewestFilms(maxNew);
	}
	
	public static List<Film> findFilmsByAttributes(
			String title, String description, int length, FilmRating rating) {
		return new FilmDAO().findFilmsByAttributes(title, description, length, rating);
	}
	
	public static List<Film> findFilmsByCategory(FilmCategory category) {
		return new FilmDAO().findFilmsByCategory(category);
	}
	
	public static List<Film> findFilmsAlphabetically(String firstCharacter) {
		return new FilmDAO().findFilmsAlphabetically(firstCharacter);
	}
	
	public static Customer findCustomerByEmail(String emailAddress) {
		return new CustomerDAO().findCustomerByEmail(emailAddress);
	}
	
	public static List<Actor> findActorsInFilm(Film film) {
		return new FilmDAO().findActorsInFilm(film);
	}
	
	public static String getFilmCategory(Film film) {
		return new FilmDAO().getFilmCategory(film);
	}
	
	public static Film getFilmDetail(Film film) {
		return new FilmDAO().getFilmDetail(film);
	}
}

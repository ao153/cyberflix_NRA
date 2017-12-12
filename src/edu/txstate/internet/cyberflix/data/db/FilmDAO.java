package edu.txstate.internet.cyberflix.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.helper.FilmFactory;

/**
 * @author Two
 *
 */
public class FilmDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());

	private static final String FILM_SELECT_STRING = "SELECT film.film_id, film.title, film.description,"
			+ "film.length, film.rating, film.release_year ";

	public List<Film> findNewestFilms(int maxNew) {
		String selectString = "SELECT film.film_id, film.title, film.description, film.length, film.rating, film.release_year "
				+ "from film" + " order by release_year desc" + " limit " + maxNew;
		List<Film> films = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			films = buildResults(results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findNewestFilms: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return films;
	}
	
	public Film findFilmByID(int filmID) {
		String selectString = "SELECT film.film_id, film.title, film.description, film.length, film.rating, film.release_year "
				+ "FROM film" + " WHERE" + " film_id = " + filmID;
		List<Film> films = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			films = buildResults(results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findFilmByID: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return films.get(0);
	}

	public List<Film> findFilmsByAttributes(String title, String description, int length, FilmRating rating) {
		String selectString = buildString(title, description, length, rating);
		List<Film> films = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			System.out.println(selectString);
			ResultSet results = statement.executeQuery(selectString);
			films = buildResults(results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findFilmsByAttributes: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return films;
	}
	
	public List<Film> findFilmsByCategory(FilmCategory category) {
		final String CATEGORY_CLAUSES = "FROM film, film_category WHERE "
				+ "film.film_id = film_category.film_id AND "
				+ "film_category.category_id =";	
		
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(CATEGORY_CLAUSES);
		stringBuilder.append(category.ordinal());
		String selectString = stringBuilder.toString();
		List<Film> films = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			films = buildResults(results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findFilmsByCategory: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return films;
	}
	
	public List<Film> findFilmsAlphabetically(String firstCharacter){
        String alphabetString = "select film_id, title, description, release_year, "
                + "length, rating  from film  where title like '";
        
        StringBuilder stringBuilder = new StringBuilder(alphabetString);
        stringBuilder.append(firstCharacter);
        stringBuilder.append("%';");
        String selectString = stringBuilder.toString();
        
        List<Film> films = null;
        Connection dbConnection = null;
        try {
            dbConnection = DAO.getDBConnection();
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(selectString);
            films = buildResults(results);
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println("FilmDAO.findFilmsAlphabetically: " + e.toString());
            LOGGER.severe(e.toString());
            closeQuietly(dbConnection);
        }
        return films;
    }
	
	public List<Actor> findActorsInFilm(Film film) {
		return new ActorDAO().findActorsInFilm(film);
	}
	
	public String getFilmCategory(Film film) {
		final String categoryFilmID = ", film_category.category_id,"
				+ " category.category_id, `name` FROM film, film_category, "
				+ "category WHERE film.film_id = film_category.film_id AND "
				+ "category.category_id = film_category.category_id AND "
				+ "film_category.film_id = " + film.getFilmID();
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(categoryFilmID);
		String selectString = stringBuilder.toString();
		String category = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			while (results.next()) {
				category = results.getString("name");
			}
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.getFilmCategory: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}
		return category;
	}
	
	public Film getFilmDetail(Film film) {
		for (Actor actor : findActorsInFilm(film)) 
			film.addActor(actor);
		film.setCategory(getFilmCategory(film));
		return film;
	}

	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to films
	}

	/**
	 * Build the WHERE clause for this query based on the parameters passed. If a
	 * string parameter is null or the empty string it won't be used. If an integer
	 * parameter is zero, it won't be used. If rating is null or unrated (UR) it
	 * won't be used
	 * 
	 * @param titleSubstring
	 *            matches a substring in the movie's title
	 * @param descriptionSubstring
	 *            matches a substring in the movie's description
	 * @param lengthMaximum
	 *            matches any movie of running time lengthMaximum or less
	 * @param ratingMaximum
	 *            matches any movie of with specified ratingMaximum or less
	 * @return String
	 */
	private String buildString(String titleSubstring, String descriptionSubstring, int lengthMaximum,
			FilmRating ratingMaximum) {
		final String OPENING_QUOTED_WILD_CARD = "'%";
		final String CLOSING_QUOTED_WILD_CARD = "%'";

		int numberWhereClauses = 0;
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(" FROM ").append(getDBName()).append(".film").append(" WHERE ");

		// create the clause to select any movie that contains titleSubstring in its
		// title
		if ((titleSubstring != null) && (titleSubstring != "")) {
			stringBuilder.append("title like ").append(OPENING_QUOTED_WILD_CARD).append(titleSubstring)
					.append(CLOSING_QUOTED_WILD_CARD);
			numberWhereClauses++;
		}

		// create the clause to select any movie that contains descriptionSubstring in
		// its description
		if ((descriptionSubstring != null) && (descriptionSubstring != "")) {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			stringBuilder.append("description like ").append(OPENING_QUOTED_WILD_CARD).append(descriptionSubstring)
					.append(CLOSING_QUOTED_WILD_CARD);
			numberWhereClauses++;
		}

		// create the clause to select any movie that has a running time <=
		// lengthMaximum
		if (lengthMaximum > 0) {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			stringBuilder.append("length <= ").append(String.valueOf(lengthMaximum));
			numberWhereClauses++;
		}

		// create the clause to select any movie with a rating <= ratingMaximum
		// Note: Sakila stores movie ratings as enums that can be queried by their
		// ordinal
		// values. Ordinals for MySQL enum fields are assigned in order of declaration,
		// just
		// as they are in Java; however, MySQL ordinals begin with 1, not 0
		if ((ratingMaximum != null) && (ratingMaximum != FilmRating.UR)) {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			int databaseRatingOrdinal = ratingMaximum.ordinal() + 1;
			stringBuilder.append("rating <= ").append(databaseRatingOrdinal);
			numberWhereClauses++;
		}
		String selectString = stringBuilder.toString();
		// LOGGER.info(selectString); <-- uncomment to see query string
		return selectString;
	}

	private static List<Film> buildResults(ResultSet results) {
		ArrayList<Film> films = new ArrayList<Film>();
		FilmFactory filmFactory = new FilmFactory();
		try {
			while (results.next()) {
				int idnum = results.getInt("film_id");
				String id = Integer.toString(idnum);
				String filmTitle = results.getString("title");
				String filmDescription = results.getString("description");
				int filmLengthnum = results.getInt("length");
				String filmLength = Integer.toString(filmLengthnum);
				String filmRating = results.getString("rating");
				String release_year = results.getString("release_year").substring(0, 4);
				Film film = filmFactory.makeFilm(id, filmTitle, filmDescription, release_year, filmLength, filmRating);
				films.add(film);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return films;
	}
}
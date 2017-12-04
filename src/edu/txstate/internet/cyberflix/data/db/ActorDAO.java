package edu.txstate.internet.cyberflix.data.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.actor.Actor;

public class ActorDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());
	
	final static String QUERY_STATEMENT = "SELECT actor_id, first_name, last_name "
										 + "FROM actor WHERE actor_id IN " 
										 + "(SELECT actor_id FROM film_actor WHERE film_id =";
										 
	public List<Actor> findActorsInFilm(Film film) {
		List<Actor> actors = null;
		Connection dbConnection = null;
		String selectString = QUERY_STATEMENT + film.getFilmID() + ")";
		
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet results = statement.executeQuery(selectString);
			actors = new ArrayList<>();
			while (results.next()) {
				actors.add(
					new Actor(
						results.getInt("actor_id"), 
						results.getString("first_name"), 
						results.getString("last_name")
			));
			}
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("ActorsDAO.findActorsInFilm: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}

		return actors;
	}

	@Override
	public void save(Object anObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}

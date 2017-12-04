package edu.txstate.internet;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.customer.Customer;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import java.util.List;

public class IndexBean implements java.io.Serializable {
	public List <Film> newFilms = DataSource.findNewestFilms(5);

   public IndexBean() {
   }
   public List<Film> getNewFilms(){
      return newFilms;
   }
}
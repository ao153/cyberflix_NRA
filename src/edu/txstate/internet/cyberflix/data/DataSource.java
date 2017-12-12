package edu.txstate.internet.cyberflix.data;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.txstate.internet.Cart;
import edu.txstate.internet.CartManager;
import edu.txstate.internet.StaffManager;
import edu.txstate.internet.UserManager;
import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.customer.Customer;
import edu.txstate.internet.cyberflix.data.customer.RentalRecord;
import edu.txstate.internet.cyberflix.data.db.CustomerDAO;
import edu.txstate.internet.cyberflix.data.db.FilmDAO;
import edu.txstate.internet.cyberflix.data.db.RentalDAO;
import edu.txstate.internet.cyberflix.data.db.StaffDAO;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import edu.txstate.internet.cyberflix.data.staff.Staff;

public class DataSource {	
	public static void init() {
		CartManager cartSystem = CartManager.getInstance();
		UserManager userSystem = UserManager.getInstance();
		StaffManager staffSystem = StaffManager.getInstance();
	}
	
    public static Staff findStaffByEmail(String emailAddress) {
        return new StaffDAO().findStaffByEmail(emailAddress);
    }
    /*
    public static void logInAs(Staff staff) {
        StaffManager.getInstance().logInAs(staff);
    }
	*/
    
    public static List<RentalRecord> findCheckedOutRentals() {
    		return new RentalDAO().findCheckedOutRentals();
    }
   
	public static void createCartAt(String key) {
		CartManager.getInstance().newCartAt(key);
	}
	
	public static void addFilmToCart(String key, Film film) {
		CartManager.getInstance().getCart(key).addFilm(film);
	}
	
	public static Cart getCart(String key) {
		return CartManager.getInstance().getCart(key);
	}
	
	public static Cart getCart(HttpSession session) {
		return CartManager.getInstance().getCart(session);
	}
	
	public static void logInAs(Customer customer) {
		UserManager.getInstance().logInAs(customer);
	}
	
	public static Customer getUser() {
		if (UserManager.getInstance().getUser() != null)
			return UserManager.getInstance().getUser();
		else if (StaffManager.getInstance().getUser() != null)
			return (Customer) StaffManager.getInstance().getUser();
		else
			return null;
	}
	
	public static Customer findCustomerByID(int userID) {
		return new CustomerDAO().findCustomerByID(userID);
	}
	
	public static Film findFilmByID(int filmID) {
		return new FilmDAO().findFilmByID(filmID);
	}
	
	public static Film findFilmByTitle (String title) {
		return new FilmDAO().findFilmsByAttributes(title, null, 0, null).get(0);
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
	
	public static void saveNewCustomer(Customer customer) {
		new CustomerDAO().save(customer);
	}
	
	public static void saveNewRental(RentalRecord record) {
		new RentalDAO().save(record);
	}
	
	public static List<RentalRecord> findRentalByCustomer(Customer customer) {
		return new RentalDAO().findRentalByCustomer(customer);
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

package edu.txstate.internet;

import java.util.ArrayList;
import java.util.List;

import edu.txstate.internet.cyberflix.data.film.*;

public class Cart {
	private ArrayList<Film> myCart;
	
	public Cart() {
		myCart = new ArrayList<>();
	}
	
	public List<Film> getCartFilms() {
		return myCart;
	}
	
	public void addFilm(Film film) {
		myCart.add(film);
	}
	
	public int getSize() {
		return myCart.size();
	}
}

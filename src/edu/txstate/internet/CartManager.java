package edu.txstate.internet;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.film.Film;

public class CartManager {
	private HashMap<String, Cart> cartStorage;
	private static CartManager instance = null;

	public static CartManager getInstance() {
		if (instance == null) {
			instance = new CartManager();
		}
		return instance;
	}

	private CartManager () {
		cartStorage = new HashMap<>();
	}
	
	public Cart getCart(String key) {
		return cartStorage.get(key);
	}
	
	public Cart getCart(HttpSession session) {
		if (session.isNew() == true)
			newCartAt(session.getId());
		return getCart(session.getId());
	}
	
	public void newCartAt(String key) {
		cartStorage.put(key, new Cart());
	}
	
}

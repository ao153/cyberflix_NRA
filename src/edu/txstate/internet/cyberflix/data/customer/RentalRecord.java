package edu.txstate.internet.cyberflix.data.customer;

import java.sql.Date;

public class RentalRecord {
	private int rentalId;
	private Date rentalDate;
	private int filmId;
	private int customerId;
	private Date returnDate;
	
	public RentalRecord(int rentalId, Date rentalDate, int filmId, int customerId, Date returnDate) {
		super();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.filmId = filmId;
		this.customerId = customerId;
		this.returnDate = returnDate;
	}
	
	public int getRentalID() {
		return rentalId;
	}

	public void setRentalID(int rentalId) {
		this.rentalId = rentalId;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public int getFilmID() {
		return filmId;
	}

	public void setFilmID(int filmId) {
		this.filmId = filmId;
	}

	public int getCustomerID() {
		return customerId;
	}

	public void setCustomerID(int customerId) {
		this.customerId = customerId;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}

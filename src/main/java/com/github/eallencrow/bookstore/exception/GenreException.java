package com.github.eallencrow.bookstore.exception;

public class GenreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7201210669524234470L;
	
	public GenreException(String genre) {
		super("Invalid Genre: " + genre);
	}

}

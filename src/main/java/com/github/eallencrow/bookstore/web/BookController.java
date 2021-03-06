package com.github.eallencrow.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.eallencrow.bookstore.dto.Book;
import com.github.eallencrow.bookstore.dto.Book.Genre;
import com.github.eallencrow.bookstore.exception.GenreException;
import com.github.eallencrow.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping
	public Iterable<Book> getBooks() {
		return service.getBooks();
	}
	
	@GetMapping("/{id}")
	public Book findBookById(@PathVariable("id") int bookId) {
		return service.getBook(bookId);
	}
	
	@GetMapping("/search")
	public Iterable<Book> searchBooks(
			@RequestParam(required=false) String title,
			@RequestParam(required=false) String author,
			@RequestParam(required=false) String genre
			) throws GenreException {
		if (genre != null && Genre.getGenre(genre) == null) {
			throw new GenreException(genre);
		}
		Genre g = Genre.getGenre(genre);
		return service.searchBooks(title, author, g);
	}
	
}

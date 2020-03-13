package com.github.eallencrow.bookstore.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.eallencrow.bookstore.dto.Book;
import com.github.eallencrow.bookstore.dto.Book.Genre;
import com.github.eallencrow.bookstore.repository.BookRepository;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookRepository dao;

	public Book getBook(int id) {
		Optional<Book> opt = dao.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	public Iterable<Book> getBooks() {
		return dao.findAll();
	}
	
	public Iterable<Book> searchBooks(String title, String author, Genre genre) {
		if (title == null) {
			if (author == null) {
				if (genre == null) {
					return getBooks();
				} else {
					return dao.findAllByGenre(genre);
				}
			} else {
				if (genre == null) {
					return dao.findAllByAuthorContainingIgnoreCase(author);
				} else {
					return dao.findAllByAuthorContainingIgnoreCaseAndGenre(author, genre);
				}
			}
		} else {
			if (author == null) {
				if (genre == null) {
					return dao.findAllByTitleContainingIgnoreCase(title);
				} else {
					return dao.findAllByTitleContainingIgnoreCaseAndGenre(title, genre);
				}
			} else {
				if (genre == null) {
					return dao.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
				} else {
					return dao.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndGenre(title, author, genre);
				}
			}
		}		
	}
	
	public Book saveBook(Book book) {
		return dao.save(book);
	}
	
	public void deleteBook(int id) {
		Book book = getBook(id);
		dao.delete(book);
	}
}

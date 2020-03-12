package com.github.eallencrow.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.eallencrow.bookstore.dto.Book;
import com.github.eallencrow.bookstore.dto.Book.Genre;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	public Iterable<Book> findAllByTitleContainingIgnoreCase(String title);
	public Iterable<Book> findAllByAuthorContainingIgnoreCase(String author);
	public Iterable<Book> findAllByGenre(Genre genre);
	
	public Iterable<Book> findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
	public Iterable<Book> findAllByTitleContainingIgnoreCaseAndGenre(String title, Genre genre);
	public Iterable<Book> findAllByAuthorContainingIgnoreCaseAndGenre(String author, Genre genre);
	
	public Iterable<Book> findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndGenre(String title, String author, Genre genre);

}

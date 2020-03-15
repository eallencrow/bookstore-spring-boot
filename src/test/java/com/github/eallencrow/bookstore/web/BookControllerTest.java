package com.github.eallencrow.bookstore.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.github.eallencrow.bookstore.dto.Book;
import com.github.eallencrow.bookstore.service.BookService;

@SpringBootTest(
		classes = BookController.class,
		webEnvironment = WebEnvironment.RANDOM_PORT
	)
@EnableAutoConfiguration
public class BookControllerTest {
	
	@MockBean
	private BookService service;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Before
	public void setup() {
		reset(service);
	}
	
	@Test
	public void testGetAllBooks() {
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "fantasy");
		Book b2 = new Book("My Sister's Grave", "Robert Dugoni", "thriller");
		Book b3 = new Book("Stillhouse Lake", "Rachel Caine", "thriller");
		when(service.getBooks()).thenReturn(Arrays.asList(b1, b2, b3));
		Book[] books = restTemplate.getForObject("http://localhost:" + port + "/books", Book[].class);
		assertEquals(3, books.length);
		assertEquals(b1, books[0]);
		assertEquals(b2, books[1]);
		assertEquals(b3, books[2]);
	}
	
	@Test
	public void testBookSearch() {
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "fantasy");
		Book b2 = new Book("My Sister's Grave", "Robert Dugoni", "thriller");
		Book b3 = new Book("Stillhouse Lake", "Rachel Caine", "thriller");
		when(service.searchBooks(any(), any(), any())).thenReturn(Arrays.asList(b1, b2, b3));
		Book[] books = restTemplate.getForObject("http://localhost:" + port + "/books/search", Book[].class);
		assertEquals(3, books.length);
		assertEquals(b1, books[0]);
		assertEquals(b2, books[1]);
		assertEquals(b3, books[2]);
	}
	
	@Test
	public void testBookSearchInvalidGenre() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/books/search?genre=bears", String.class);
		assertEquals(500, response.getStatusCodeValue());
	}
	
	@Test
	public void testGetBookById() {
		Book b = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "fantasy");
		when(service.getBook(1)).thenReturn(b);
		Book book = restTemplate.getForObject("http://localhost:" + port + "/books/1", Book.class);
		assertEquals(b, book);
	}

}

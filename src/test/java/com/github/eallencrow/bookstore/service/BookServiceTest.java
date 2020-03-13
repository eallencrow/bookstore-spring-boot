package com.github.eallencrow.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.github.eallencrow.bookstore.dto.Book;
import com.github.eallencrow.bookstore.repository.BookRepository;

@SpringBootTest(classes = BookService.class)
public class BookServiceTest {
	
	@MockBean
	private BookRepository dao;
	
	@Autowired
	private BookService service;
	
	@Before
	public void setup() {
		reset(dao);
	}
	
	@Test
	public void testGetBookById() {
		Book b = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "fantasy");
		b.setId(1);
		when(dao.findById(Mockito.any())).thenReturn(Optional.of(b));
		Book testBook = service.getBook(1);
		assertEquals(1, testBook.getId());
		assertEquals(b, testBook);
	}
	
	@Test
	public void testGetAllBooks() {
		Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "fantasy");
		Book b2 = new Book("My Sister's Grave", "Robert Dugoni", "thriller");
		Book b3 = new Book("Stillhouse Lake", "Rachel Caine", "thriller");
		b1.setId(1);
		b2.setId(2);
		b3.setId(3);
		when(dao.findAll()).thenReturn(Arrays.asList(b1, b2, b3));
		Iterable<Book> bookList = service.getBooks();
		Iterator<Book> iterator = bookList.iterator();
		assertEquals(b1, iterator.next());
		assertEquals(b2, iterator.next());
		assertEquals(b3, iterator.next());
	}

}

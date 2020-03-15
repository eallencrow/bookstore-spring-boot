package com.github.eallencrow.bookstore;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.eallencrow.bookstore.repository.BookRepository;
import com.github.eallencrow.bookstore.service.BookService;
import com.github.eallencrow.bookstore.web.BookController;

@SpringBootTest
class BookstoreSpringBootApplicationTests {
	
	@Autowired
	private BookRepository dao;
	
	@Autowired
	private BookService service;
	
	@Autowired
	private BookController controller;

	@Test
	void contextLoads() {
		assertNotNull(dao);
		assertNotNull(service);
		assertNotNull(controller);
	}

}

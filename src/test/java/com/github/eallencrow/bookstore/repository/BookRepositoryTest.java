package com.github.eallencrow.bookstore.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.eallencrow.bookstore.dto.Book;
import com.github.eallencrow.bookstore.dto.Book.Genre;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository dao;

    @Test
    public void testInsertBook() {
        Book b = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "fantasy");
        b = dao.save(b);
        
        Book daoBook = dao.findById(b.getId()).get();
        assertEquals(b, daoBook);
    }
    
    @Test
    public void testFindByTitle() {
    	Book b = new Book("Peace Talks", "Jim Butcher", "fantasy");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByTitleContainingIgnoreCase("Peace Talks");
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByTitleLike() {
    	Book b = new Book("Burn After Writing", "Sharon Jones", "romance");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByTitleContainingIgnoreCase("burn");
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByAuthor() {
    	Book b = new Book("A Long Petal of the Sea", "Isabel Allende", "romance");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByAuthorContainingIgnoreCase("Isabel Allende");
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByAuthorLike() {
    	Book b = new Book("My Sister's Grave", "Robert Dugoni", "thriller");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByAuthorContainingIgnoreCase("dugoni");
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByGenre() {
    	Book b = new Book("Stillhouse Lake", "Rachel Caine", "thriller");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByGenre(Genre.THRILLER);
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByTitleAndAuthor() {
    	Book b = new Book("Thinking, Fast and Slow", "Daniel Kahneman", "business");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase("Thinking", "Kahneman");
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByTitleAndGenre() {
    	Book b = new Book("Emotional Intelligence 2.0", "Travis Bradberry", "business");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByTitleContainingIgnoreCaseAndGenre("Emotional", Genre.BUSINESS);
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByAuthorAndGenre() {
    	Book b = new Book("Why We're Polarized", "Ezra Klein", "history");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByAuthorContainingIgnoreCaseAndGenre("Klein", Genre.HISTORY);
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
    
    @Test
    public void testFindByTitleAndAuthorAndGenre() {
    	Book b = new Book("The Man in the Red Coat", "Julian Barnes", "history");
    	b = dao.save(b);
    	
    	Iterable<Book> books = dao.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCaseAndGenre("Coat", "Barnes", Genre.HISTORY);
    	for (Book daoBook : books) {
    		if (b.equals(daoBook)) {
    			return;
    		}
    	}
    	fail();
    }
}

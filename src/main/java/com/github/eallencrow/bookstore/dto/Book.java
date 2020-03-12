package com.github.eallencrow.bookstore.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	private String author;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
	public Book(String title, String author, String genre) {
		this.title = title;
		this.author = author;
		this.genre = Genre.getGenre(genre);
	}
		
	@Getter
	public static enum Genre {
		THRILLER("thriller"),ROMANCE("romance"),COMEDY("comedy"),FANTASY("fantasy"),SCIENCE_FICTION("science fiction"),
		MYSTERY("mystery"),BUSINESS("business"),HISTORY("history");
		
		private String value;
		
		private Genre(String text) {
			this.value = text;
		}
		
		public static Genre getGenre(String text) {
			for (Genre g : Genre.values()) {
				if (text.toLowerCase().equals(g.value)) {
					return g;
				}
			}
			return null;
		}
		
		public String toString() {
			return this.value;
		}
		
	}
}

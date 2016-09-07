package com.spring.book.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ???? on 01.07.2016.
 */

@Entity
@Table(name = "BOOK")
public class Book {

	private Long id;
	private String title;
	private Float price;
	private String description;
	private Integer nbOfPage;
	private Set<Author> authors = new HashSet<Author>();

	public Book() {
	}

	public Book(String title, Float price, String description, Integer nbOfPage) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.nbOfPage = nbOfPage;
	}

	public Book(Long id, String title, Float price, String description, Integer nbOfPage, Set<Author> authors) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.nbOfPage = nbOfPage;
		this.authors = authors;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PRICE")
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "NBOFPAGE")
	public Integer getNbOfPage() {
		return nbOfPage;
	}

	public void setNbOfPage(Integer nbOfPage) {
		this.nbOfPage = nbOfPage;
	}

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable(name = "BOOK_AUTHOR",
	joinColumns = @JoinColumn(name = "BOOK_ID"),
	inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
	@JsonManagedReference
	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;

		Book book = (Book) o;

		if (getId() != null ? !getId().equals(book.getId()) : book.getId() != null) return false;
		if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
		if (getPrice() != null ? !getPrice().equals(book.getPrice()) : book.getPrice() != null) return false;
		if (getDescription() != null ? !getDescription().equals(book.getDescription()) : book.getDescription() != null)
			return false;
		if (getNbOfPage() != null ? !getNbOfPage().equals(book.getNbOfPage()) : book.getNbOfPage() != null)
			return false;
		return getAuthors() != null ? getAuthors().equals(book.getAuthors()) : book.getAuthors() == null;

	}



}

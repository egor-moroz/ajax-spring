package com.spring.book.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ???? on 01.07.2016.
 */

@Entity
@Table(name = "AUTHOR")
public class Author {

	private Long id;
	private String name;
	private Set<Book> books = new HashSet<Book>();

	public Author() {
	}

	/*public Author(String name) {
		this.name = name;
	}*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "BOOK_AUTHOR",
	joinColumns = @JoinColumn(name = "AUTHOR_ID" ),
	inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
	@JsonBackReference
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Author)) return false;

		Author author = (Author) o;

		if (getId() != null ? !getId().equals(author.getId()) : author.getId() != null) return false;
		if (getName() != null ? !getName().equals(author.getName()) : author.getName() != null) return false;
		return getBooks() != null ? getBooks().equals(author.getBooks()) : author.getBooks() == null;

	}



}

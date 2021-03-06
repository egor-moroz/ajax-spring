package com.spring.book.service;

import com.spring.book.model.Author;
import com.spring.book.model.Book;

import java.util.List;

/**
 * Created by ???? on 01.07.2016.
 */
public interface BookService {

	List<Book> findAllBooks();

	List<Author> findAllAuthors();

	void saveAuthor(Author author);

	void saveBook(Book book);

	List<Book> findBookByName(String name);

	Book findBookById(Long id);

	Author findAuthorById(Long id);

	List<Book> findBookByAuthorName(String AuthorName);

	void deleteAuthor(Author author);

	void deleteBook(Long id);


}

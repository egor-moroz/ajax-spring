package com.spring.book.service;

import com.spring.book.model.Author;
import com.spring.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.spring.book.repository.AuthorRepository;
import com.spring.book.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ???? on 01.07.2016.
 */

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;

	/*@Autowired
	public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}*/


	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public void saveAuthor(Author author) {
		authorRepository.save(author);
	}

	public void saveBook(Book book) {
		System.out.println("book id in service = "+book.getId());
		bookRepository.save(book);
	}

	public List<Book> findBookByName(String name) {
		return bookRepository.findByName(name);
	}

	public Book findBookById(Long id) {
		return bookRepository.findById(id);
	}

	public Author findAuthorById(Long id) {
		return authorRepository.findById(id);
	}

	public List<Book> findBookByAuthorName(String AuthorName) {
		return bookRepository.findByAuthorName(AuthorName);
	}

	public void deleteAuthor(Author author) {
		authorRepository.delete(author);
	}

	public void deleteBook(Long id) {
		bookRepository.deleteBook(id);
	}
}

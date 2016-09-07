package com.spring.book.converter;

import com.spring.book.model.Author;
import com.spring.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by ???? on 06.07.2016.
 */

@Component
public class BookToAuthorConverter implements Converter<Object, Author> {

	@Autowired
	BookService bookService;

	@Override
	public Author convert(Object o) {
		System.out.println("I am in converter-------------");
		Long id = Long.parseLong((String)o);
		Author author = bookService.findAuthorById(id);
		System.out.println("Author = "+ author);
		System.out.println("I am out from converter-------------");
		return author;
	}
}

package com.spring.book.web;

import com.spring.book.model.Author;
import com.spring.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;




@Component
public class Editor extends PropertyEditorSupport {

	@Autowired
	BookService bookService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		System.out.println("I am in Editor/////");
		System.out.println("text = "+text);
		Author author=bookService.findAuthorById(Long.parseLong(text));
		System.out.println("Author id  = "+author.getId());
		System.out.println("I am OUT Editor/////");
		setValue(author);
	}
}

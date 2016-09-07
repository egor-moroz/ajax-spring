package com.spring.book.web;

import com.spring.book.model.Author;
import com.spring.book.model.Book;
import com.spring.book.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.spring.book.service.BookService;

import java.util.List;

/**
 * Created by ???? on 01.07.2016.
 */

@Controller
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<Author> saveAuthor (@ModelAttribute("author")Author author, Model model){
		bookService.saveAuthor(author);
		return bookService.findAllAuthors();
	}
	@RequestMapping(value = "/{id}",method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<Author> editAuthor (@ModelAttribute("author")Author author, @PathVariable("id") Long id, Model model){
		author.setId(id);
		System.out.println("id in author controller = "+id);
		bookService.saveAuthor(author);
		return bookService.findAllAuthors();
	}



}

package com.spring.book.web;

import com.spring.book.model.Author;
import com.spring.book.model.Book;
import com.spring.book.service.BookService;
import com.spring.book.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Set;

/**
 * Created by ???? on 01.07.2016.
 */
@Controller
@RequestMapping("/book")
public class BookController {


	@Autowired
	BookServiceImpl bookService;

	@RequestMapping(method = RequestMethod.GET)
	public String listBooks (Model model){
		model.addAttribute("bookList", bookService.findAllBooks());
		return "/list-books";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookService.findBookById(id));
		model.addAttribute("authors",bookService.findAllAuthors());
		model.addAttribute("author", new Author());
		return "/add-book";
	}
	@RequestMapping(value = "/authors",method = RequestMethod.GET)
	public String listAuthors (Model model){
		model.addAttribute("authors", bookService.findAllAuthors());
		return "/list-books";
	}

	@RequestMapping(method = RequestMethod.POST ,value = "/{id}")
	public String editBook(Book book, @PathVariable("id") Long id, Model model) {
		bookService.saveBook(book);
	/*	model.addAttribute("authors",bookService.findAllAuthors());*/
		model.addAttribute("bookList", bookService.findAllBooks());
		return "/list-books";
	}

	@RequestMapping(method = RequestMethod.POST )
	public String addBook(Book book , Model model) {
		bookService.saveBook(book);
		/*model.addAttribute("authors",bookService.findAllAuthors());*/
		model.addAttribute("bookList", bookService.findAllBooks());
		return "/list-books";
	}

	@RequestMapping(method = RequestMethod.GET, params = "addForm")
	public String addForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("author", new Author());
		return "/add-book";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"nameSearch", "searchAction"})
	public String searchByName(@RequestParam("nameSearch") String nameSearch, @RequestParam("searchAction")
			String searchAction, Model model) {
		switch(searchAction){
			case "searchByBookTitle":
				model.addAttribute("bookList", bookService.findBookByName(nameSearch));
				return "/list-books";
			case "searchByAuthorName":
				model.addAttribute("bookList", bookService.findBookByAuthorName(nameSearch));
				return "/list-books";
			default:
				model.addAttribute("bookList", bookService.findBookByName(nameSearch));
				return "/list-books";

		}
	}
	@RequestMapping( method = RequestMethod.POST, value = "delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);
		model.addAttribute("bookList", bookService.findAllBooks());
		return "/list-books";
	}


	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Author.class, new PropertyEditorSupport(){

			@Override
			public void setAsText(String text) throws IllegalArgumentException{
				Author author=bookService.findAuthorById(Long.parseLong(text));
				setValue(author);
			}
		});
	}

}

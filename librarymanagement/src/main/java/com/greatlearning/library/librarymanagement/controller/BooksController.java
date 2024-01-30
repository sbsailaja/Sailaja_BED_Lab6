package com.greatlearning.library.librarymanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.library.librarymanagement.model.Book;
import com.greatlearning.library.librarymanagement.service.BookService;


@Controller
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	BookService service;
	
	@RequestMapping("/list")
	public String getAllBooks(Model model) {
		List<Book> books = service.getAllBooks();
		model.addAttribute("books",books);
		return "books/list-books";
	}
	
	@GetMapping("/showFormForAdd")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book",book);
		return "books/book-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String updateBook(Model model,@RequestParam("bookId") int bookId) {
		Book book = service.findById(bookId);
		model.addAttribute("book",book);
		return "books/book-form";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("bookId") int bookId) {
		service.deleteById(bookId);
		return "redirect:/books/list";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("book") Book book) {
		service.save(book);
		return "redirect:/books/list";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("name") String name,@RequestParam("author") String author,Model model) {
		List<Book> books = service.searchBooksByNameAndAuthor(name, author);
		model.addAttribute("books",books);
		return "books/list-books";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("books/403");
		return model;

	}
}

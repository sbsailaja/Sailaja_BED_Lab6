package com.greatlearning.library.librarymanagement.service;

import java.util.List;
import com.greatlearning.library.librarymanagement.model.Book;

public interface BookService {
	public List<Book> getAllBooks();
	public void save(Book book); // shared by insert and update
	public void deleteById(int id);
	public Book findById(int id);
	public List<Book> searchBooksByNameAndAuthor(String name,String author);
}

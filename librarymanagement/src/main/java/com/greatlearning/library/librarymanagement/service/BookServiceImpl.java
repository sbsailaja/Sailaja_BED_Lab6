package com.greatlearning.library.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.library.librarymanagement.model.Book;
import com.greatlearning.library.librarymanagement.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookRepository repository;
	@Override
	public List<Book> getAllBooks() {
		return repository.findAll();
	}
	@Override
	public void save(Book book) {
		repository.save(book);
	}
	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	@Override
	public Book findById(int id) {
		Optional<Book> optBook = repository.findById(id);
		if(optBook.isPresent()) {
			return optBook.get();
		}else {
			throw new RuntimeException("Book Id is not present");
		}
	}
	@Override
	public List<Book> searchBooksByNameAndAuthor(String name, String author) {
		if(name.isEmpty() && author.isEmpty()) {
			return getAllBooks();
		}
		return repository.findByNameContainsAndAuthorContainsAllIgnoreCase(name, author);
	}

}

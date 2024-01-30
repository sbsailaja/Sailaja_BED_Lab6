package com.greatlearning.library.librarymanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.library.librarymanagement.model.Book;


public interface BookRepository extends JpaRepository<Book, Integer>{
	//Customized Query through MethodName Signature or Using @Query annotation.
	List<Book> findByNameContainsAndAuthorContainsAllIgnoreCase(String name,String author);
}

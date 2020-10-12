package com.demo.spring.service;

import com.demo.spring.model.Book;
import com.demo.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public String create(Book book) {
         bookRepository.save(book);
         return "Success";
    }
}

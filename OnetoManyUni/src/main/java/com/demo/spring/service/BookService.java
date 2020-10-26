package com.demo.spring.service;

import com.demo.spring.exception.ResourceNotFoundException;
import com.demo.spring.model.Book;
import com.demo.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Book> getBook(int id) {
        return bookRepository.findById(id);
    }

    public Book update(int id, Book book) throws ResourceNotFoundException {
        return bookRepository.findById(id).map(book1 -> {
            book1.setNumOfPages(book.getNumOfPages());
            book1.setTitle(book.getTitle());
            book1.setAuthors(book.getAuthors());
            book1.setPrice(book.getPrice());
            return bookRepository.save(book1);
        }).orElseThrow(() -> new ResourceNotFoundException("Book Not found"));

    }

    public String delete(int id) throws ResourceNotFoundException {
        return bookRepository.findById(id).map(book -> { bookRepository.deleteById(id);
        return "Book details deleted successfully";
        }).orElseThrow(() -> new ResourceNotFoundException("Book Not found"));
    }
}

package com.demo.spring.service;

import com.demo.spring.exception.ResourceNotFoundException;
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
         book.getAuthors().forEach(i->i.setBook(book));
         bookRepository.save(book);
         return "Success";
    }

    public Book getBook(int id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Book Found"));
    }

    public Book update(int id, Book book) throws ResourceNotFoundException {
        book.getAuthors().forEach(i->i.setBook(book));
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

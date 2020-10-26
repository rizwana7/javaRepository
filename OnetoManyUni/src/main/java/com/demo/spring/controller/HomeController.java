package com.demo.spring.controller;

import com.demo.spring.exception.ResourceNotFoundException;
import com.demo.spring.model.Book;
import com.demo.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/getBooks")
    public ResponseEntity<List<Book>> Books(){
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping(value="/getBook/{id}")
    public ResponseEntity<Optional<Book>> Book(@PathVariable int id){
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping(value = "/create",consumes = {MediaType.APPLICATION_XML_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> create(@RequestBody Book book){
        return ResponseEntity.ok(bookService.create(book));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody Book book) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookService.update(id, book));

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(bookService.delete(id));
    }


}

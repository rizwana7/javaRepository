package com.demo.spring.controller;

import com.demo.spring.model.Book;
import com.demo.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/get",produces = MediaType.TEXT_HTML_VALUE)
    public String getMessage(){
        return "Hello Rizwana....";
    }

    @GetMapping(value = "/getBook")
    public List<Book> getBooks(){
       return bookService.getBooks();
    }

    @PostMapping(value = "/create",consumes = {MediaType.APPLICATION_XML_VALUE,
                                                MediaType.APPLICATION_JSON_VALUE})
    public String createBook(@RequestBody Book book){
        return bookService.create(book);
    }
}

package com.demo.spring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int id;
    private int numOfPages;
    private String title;
    private double price;

    @OneToMany(targetEntity = Author.class,cascade = CascadeType.ALL,
                              fetch = FetchType.LAZY,orphanRemoval = true,mappedBy = "book")
    private List<Author> authors = new ArrayList<>();

    public void addAuthors(Author author){
        this.authors.add(author);
        author.setBook(this);
    }

    public void removeAuthors(Author author){
        this.authors.remove(author);
        author.setBook(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonManagedReference
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", numOfPages=" + numOfPages +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", price=" + price +
                '}';
    }
}

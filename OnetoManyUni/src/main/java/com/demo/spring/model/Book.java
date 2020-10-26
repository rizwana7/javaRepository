package com.demo.spring.model;

import javax.persistence.*;
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

    /*for foreign key association creates 3 tables:Book,Author,Book_Authors
    @OneToMany(targetEntity = Author.class,cascade = CascadeType.ALL,
                              fetch = FetchType.LAZY,orphanRemoval = true)*/

    @OneToMany(targetEntity = Author.class,cascade = CascadeType.ALL)
    @JoinColumn(name="book_id", referencedColumnName = "book_id")
    private List<Author> authors;

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

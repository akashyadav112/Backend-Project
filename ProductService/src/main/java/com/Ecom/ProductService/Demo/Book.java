package com.Ecom.ProductService.Demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String bookName;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String bookName, Author author) {
        this.bookName = bookName;
        this.author = author;
    }
}

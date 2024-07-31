package com.Libraries.Library.entity;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.security.Identity;

@Entity
@Table
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long ID;

    @Column
    private   String title;
    @Column
    private   String author;
    @Column
    private   int publication_year;
    @Column
    private  String  ISBN;

    // No-argument constructor (required by JPA)
    public Books() {
    }

    // Constructor with all fields except ID
    public Books(String title, String author, int publication_year, String ISBN) {
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.ISBN = ISBN;
    }

    // Constructor including ID
    public Books(Long ID, String title, String author, int publication_year, String ISBN) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.ISBN = ISBN;
    }
    public int getPublication_year() {
        return publication_year;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Books{" +
                "ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publication_year=" + publication_year +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}

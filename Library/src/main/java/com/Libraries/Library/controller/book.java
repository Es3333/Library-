package com.Libraries.Library.controller;
import com.Libraries.Library.Model.bookmodel;
import com.Libraries.Library.entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class book {
    @Autowired
    private bookmodel bookmod;

    @GetMapping ("/api/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        return new ResponseEntity<>(bookmod.find_all_books(), HttpStatus.OK);
    }


    @GetMapping("/api/books/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        Books book = bookmod.find_book(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@PostMapping("/api/books/add")

    public ResponseEntity<Books> add_book(@RequestBody Books book){
        return new ResponseEntity<>(bookmod.add_book(book),HttpStatus.CREATED);
}

    @PutMapping("/api/books/update/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books book) {
        Books updatedBook = bookmod.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
    @DeleteMapping("/api/books/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookmod.delete_book(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
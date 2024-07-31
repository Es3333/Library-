package com.Libraries.Library.Model;

import com.Libraries.Library.Repository.bookres;
import com.Libraries.Library.entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class bookmodel {
    @Autowired
 private bookres bres;


    public List<Books> find_all_books(){
        return bres.findAll();
    }

    public Books find_book(Long id){
return         bres.findById(id).orElse(null);
    }
    public Books add_book(Books book){
        return bres.save(book);
    }
    public void delete_book(long id){

         bres.deleteById(id);
    }
    public Books updateBook(Long id, Books book) {
        Books existingBook = bres.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublication_year(book.getPublication_year());
            existingBook.setISBN(book.getISBN());
            return bres.save(existingBook);
        }
        return null;
    }
}

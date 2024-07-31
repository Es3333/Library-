package com.Libraries.Library.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Recordentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    private Date borrowDate;
    private Date returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "id=" + id +
                ", book=" + book +
                ", patron=" + patron +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

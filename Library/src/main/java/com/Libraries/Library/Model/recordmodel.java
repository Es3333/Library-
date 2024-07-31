package com.Libraries.Library.Model;

import com.Libraries.Library.Repository.BorrowingRecordRepository;
import com.Libraries.Library.entity.Books;
import com.Libraries.Library.entity.Recordentity;
import com.Libraries.Library.entity.Patron;
import com.Libraries.Library.Repository.partonres;
import com.Libraries.Library.Repository.bookres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class recordmodel
{
    @Autowired
   private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private bookres bookRepository;

    @Autowired
    private partonres patronRepository;

    public Recordentity borrowBook(Long bookId, Long patronId) {
        Books book = bookRepository.findById(bookId).orElse(null);
        Patron patron = patronRepository.findById(patronId).orElse(null);
        if (book != null && patron != null) {
            Recordentity borrowingRecord = new Recordentity() ;
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowDate(new Date());
            return borrowingRecordRepository.save(borrowingRecord);
        }
        return null;
    }
    public Recordentity returnBook(Long Id) {

        Recordentity record= borrowingRecordRepository.getById(Id);
        if (Id != null) {
            record.setReturnDate(new Date());
            return borrowingRecordRepository.save(record);
        }
        return null;
    }
}

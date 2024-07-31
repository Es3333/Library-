package com.Libraries.Library.controller;

import com.Libraries.Library.Model.recordmodel;
import com.Libraries.Library.entity.Recordentity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class Record {
    @Autowired
    private recordmodel reco;

    @PostMapping("/api/book/{bookid}/parton/{patronid}")
    public Recordentity borrowBook(@PathVariable Long bookid, @PathVariable Long patronid) {
        return reco.borrowBook(bookid, patronid);
    }



@PutMapping("api/record/{id}")
public Recordentity returnbook(@PathVariable Long id){
        return reco.returnBook(id);
}



}

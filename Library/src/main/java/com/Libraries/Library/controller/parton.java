package com.Libraries.Library.controller;
import com.Libraries.Library.Model.partonmodel;
import com.Libraries.Library.entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class parton {
    @Autowired
 private    partonmodel partonmodel;



    @GetMapping("/api/patrons")
    public ResponseEntity<List<Patron>> getAllPatrons() {
        return new ResponseEntity<>(partonmodel.findAllPatrons(), HttpStatus.OK);
    }


    @GetMapping("api/parton/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable Long id) {
        return new ResponseEntity<>(partonmodel.find_parton(id), HttpStatus.OK);
    }
    @PostMapping("api/parton/add")
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        return new ResponseEntity<>(partonmodel.add(patron), HttpStatus.OK);
    }
    @PutMapping("api/parton/update/{id}")
    public ResponseEntity<Patron> UpdatePatron(@PathVariable Long id,@RequestBody Patron patron)  {
        return new ResponseEntity<>(partonmodel.updatePatron(id,patron), HttpStatus.OK);
    }
    @DeleteMapping("api/parton/delete/{id}")
    public ResponseEntity<Void> deleteparton(@PathVariable Long id) {
        partonmodel.deletePatron(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

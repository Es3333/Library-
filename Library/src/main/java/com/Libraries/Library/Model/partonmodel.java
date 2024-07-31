package com.Libraries.Library.Model;

import com.Libraries.Library.Repository.partonres;
import com.Libraries.Library.entity.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class partonmodel {

    @Autowired
    partonres parton ;

    @ResponseBody
    public List<Patron> findAllPatrons() {
        return parton.findAll();
    }

    public Patron find_parton(long id){
        return parton.findById(id).orElse(null);
    }

    public  Patron add(Patron patron){
        return parton.save(patron);
}
    public Patron updatePatron(Long id, Patron patron) {
        Patron existingPatron = parton.findById(id).orElse(null);
        if (existingPatron != null) {
            existingPatron.setName(patron.getName());
            existingPatron.setPhone_number(patron.getPhone_number());
            return parton.save(existingPatron);
        }
        return null;
    }

    public void deletePatron(Long id) {
        parton.deleteById(id);
    }


}

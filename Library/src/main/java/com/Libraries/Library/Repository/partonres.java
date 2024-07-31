package com.Libraries.Library.Repository;


import com.Libraries.Library.entity.Patron;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface partonres extends JpaRepository<Patron,Long> {

}

package com.Libraries.Library.Repository;


import com.Libraries.Library.entity.Books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookres extends JpaRepository<Books, Long> {
}
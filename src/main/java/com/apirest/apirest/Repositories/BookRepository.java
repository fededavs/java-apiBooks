package com.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apirest.apirest.Entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

package com.kodilla.library.dao;

import com.kodilla.library.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Long> {

}

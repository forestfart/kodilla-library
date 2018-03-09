package com.kodilla.library.dao;

import com.kodilla.library.Exemplar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExemplarDao extends CrudRepository<Exemplar, Long> {
    List<Exemplar> findExemplarsByBookId(long bookId);
}

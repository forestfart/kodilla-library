package com.kodilla.library.dao;

import com.kodilla.library.Book;
import com.kodilla.library.Exemplar;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExemplarDaoTestSuite {

    @Autowired
    private ExemplarDao exemplarDao;
    @Autowired
    private BookDao bookDao;

    @Test
    public void testSaveExemplar() {

        // Given
        Book testBook = new Book("test", "test",2000);
        bookDao.save(testBook);

        Exemplar newExemplar = new Exemplar(testBook.getId());

        // When
        exemplarDao.save(newExemplar);
        long id = newExemplar.getId();
        Exemplar fetchedExemplar = exemplarDao.findById(id).get();

        // Then
        Assert.assertThat(fetchedExemplar.getId(), Matchers.equalTo(id));

        // Clean-up
        exemplarDao.delete(newExemplar);
        bookDao.delete(testBook);
    }

    @Test
    public void testModifyExemplarStatus() {

        // Given
        Book testBook = new Book("testModify", "testModify",2000);
        bookDao.save(testBook);

        Exemplar newExemplar = new Exemplar(testBook.getId());
        exemplarDao.save(newExemplar);

        // When
        Exemplar fetchedExemplar = exemplarDao.findById(newExemplar.getId()).get();
        fetchedExemplar.setStatus("Change exemplar #23 status test");
        exemplarDao.save(fetchedExemplar);

        // Then
        Assert.assertThat(fetchedExemplar.getStatus(), Matchers.equalTo(exemplarDao.findById(newExemplar.getId()).get().getStatus()));

        // Clean-up
        exemplarDao.delete(newExemplar);
        bookDao.delete(testBook);
    }

    @Test
    public void testFetchNumberOfBookExemplars(){

        // Given
        Book testBook = new Book("Testing Number Of Exemplars", "Test",2000);
        bookDao.save(testBook);

        Exemplar newExemplar1 = new Exemplar(testBook.getId());
        Exemplar newExemplar2 = new Exemplar(testBook.getId());
        exemplarDao.save(newExemplar1);
        exemplarDao.save(newExemplar2);

        // When
        List<Exemplar> foundExemplars = exemplarDao.findExemplarsByBookId(testBook.getId());

        // Then
        Assert.assertThat(foundExemplars.size(), Matchers.equalTo(2));

        // Clean-up
        exemplarDao.delete(newExemplar1);
        exemplarDao.delete(newExemplar2);
        bookDao.delete(testBook);
    }
}

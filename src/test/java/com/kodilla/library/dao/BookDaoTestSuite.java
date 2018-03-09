package com.kodilla.library.dao;

import com.kodilla.library.Book;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTestSuite {

    @Autowired
    private  BookDao bookDao;

    @Test
    public void testBookDaoSave() {

        // Given
        Book book = new Book("Mysterious Island", "Jules Verne", 1874);

        // When
        bookDao.save(book);
        long id = book.getId();
        Optional<Book> fetchedBook = bookDao.findById(id);

        // Then
        Assert.assertThat(fetchedBook.get().getId(), Matchers.equalTo(id));
        bookDao.deleteById(id);
    }

}

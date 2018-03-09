package com.kodilla.library.dao;

import com.kodilla.library.Book;
import com.kodilla.library.Exemplar;
import com.kodilla.library.Rental;
import com.kodilla.library.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentDaoTestSuite {

    @Autowired
    RentalDao rentalDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    ExemplarDao exemplarDao;
    @Autowired
    UserDao userDao;

    @Test
    public void testBookRent() {

        // Given
        User newUser = new User("Mark", "Twain");
        userDao.save(newUser);

        Book testBook = new Book("test Rent a Book", "test",2000);
        bookDao.save(testBook);

        Exemplar testExemplar = new Exemplar(testBook.getId());
        exemplarDao.save(testExemplar);

        // When
        Rental rentTestBook = new Rental(newUser.getId(),testExemplar.getId(), LocalDateTime.now(), null);
        rentalDao.save(rentTestBook);

        Rental fetchedRent = rentalDao.findById(rentTestBook.getRentId()).get();

        // Then
        Assert.assertThat(rentTestBook.getRentId(), Matchers.equalTo(fetchedRent.getRentId()));


        // Clean-up
        rentalDao.delete(rentTestBook);
        exemplarDao.delete(testExemplar);
        bookDao.delete(testBook);
        userDao.delete(newUser);
    }

    @Test
    public void testBookReturn() {
        // Given
        User newUser = new User("Mark", "Twain");
        userDao.save(newUser);

        Book testBook = new Book("test Rent a Book", "test",2000);
        bookDao.save(testBook);

        Exemplar testExemplar = new Exemplar(testBook.getId());
        exemplarDao.save(testExemplar);

        Rental rentTestBook = new Rental(newUser.getId(),testExemplar.getId(), LocalDateTime.of(2015,02,02,15,10,20), null);
        rentalDao.save(rentTestBook);

        Rental fetchedRent = rentalDao.findById(rentTestBook.getRentId()).get();
        fetchedRent.setReturnDate(LocalDateTime.of(2018,02,02,15,10,20));
        rentalDao.save(fetchedRent);

        // When
        Rental checkRentalReturn = rentalDao.findById(rentTestBook.getRentId()).get();

        // Then
        Assert.assertThat(fetchedRent.getReturnDate(), Matchers.equalTo(checkRentalReturn.getReturnDate()));

        // Clean-up
        rentalDao.delete(rentTestBook);
        exemplarDao.delete(testExemplar);
        bookDao.delete(testBook);
        userDao.delete(newUser);
    }
}

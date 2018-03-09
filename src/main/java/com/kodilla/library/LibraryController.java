package com.kodilla.library;

import com.kodilla.library.dao.*;
import com.kodilla.library.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("kodilla/library")
public class LibraryController {
    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    ExemplarDao exemplarDao;
    @Autowired
    RentalDao rentalDao;
    @Autowired
    LibraryMapper libraryMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        userDao.save(libraryMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookDao.save(libraryMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addExemplar", consumes = APPLICATION_JSON_VALUE)
    public void addExemplar(@RequestBody ExemplarDto exemplarDto) {
        exemplarDao.save(libraryMapper.mapToExemplar(exemplarDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateExemplar", consumes = APPLICATION_JSON_VALUE)
    public ExemplarDto updateExemplar(@RequestBody ExemplarDto exemplarDto) {
        Exemplar fetchedExemplar = exemplarDao.findById(exemplarDto.getId()).get();
        fetchedExemplar.setStatus(exemplarDto.getStatus());
        return libraryMapper.mapToExemplarDto(exemplarDao.save(fetchedExemplar));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getExemplars")
    public List<ExemplarDto> getExemplars(@RequestParam long bookId) {
        return libraryMapper.mapToExemplarsList(exemplarDao.findExemplarsByBookId(bookId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "rentExemplar")
    public void rentExemplar(@RequestParam long userId, long exemplarId) {
        rentalDao.save(new Rental(userId,exemplarId, LocalDateTime.now(), null));
    }

    @RequestMapping(method = RequestMethod.POST, value = "returnExemplar")
    public RentalDto returnExemplar(@RequestParam long exemplarId) {
        Rental fetchedRent = rentalDao.findById(exemplarId).get();
        fetchedRent.setReturnDate(LocalDateTime.now());
        return libraryMapper.mapToRentalDto(rentalDao.save(fetchedRent));
    }
}

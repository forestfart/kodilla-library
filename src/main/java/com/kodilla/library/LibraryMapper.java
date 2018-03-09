package com.kodilla.library;

import com.kodilla.library.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {

    public User mapToUser (final UserDto userDto){
        return new User(userDto.getName(), userDto.getSurname());
    }

    public UserDto mapToUserDto (final User user) {
        return new UserDto(user.getName(), user.getSurname());
    }

    public Book mapToBook(final BookDto bookDto) {
        return new Book(bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPublicationYear());
    }

    public Exemplar mapToExemplar(final ExemplarDto exemplarDto) {
        return new Exemplar(exemplarDto.getBookId());
    }

    public ExemplarDto mapToExemplarDto(final Exemplar exemplar) {
        return new ExemplarDto(exemplar.getId(), exemplar.getBookId(), exemplar.getStatus());
    }

    public List<ExemplarDto> mapToExemplarsList(final List<Exemplar> exemplarList) {
        return exemplarList.stream().map(e -> new ExemplarDto(e.getId(), e.getBookId(), e.getStatus())).collect(Collectors.toList());
    }

    public RentalDto mapToRentalDto(final Rental rent) {
        return new RentalDto(rent.getUserId(), rent.getExemplarId(), rent.getRentDate(), rent.getReturnDate());
    }
}

package com.kodilla.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private long userId;
    private long exemplarId;
    private LocalDateTime rentDate;
    private LocalDateTime returnDate;
}

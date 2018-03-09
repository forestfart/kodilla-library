package com.kodilla.library;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long rentId;

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "EXEMPLAR_ID")
    private long exemplarId;

    @Column(name = "RENT_DATE")
    private LocalDateTime rentDate;

    @Column(name = "RETURN_DATE")
    private LocalDateTime returnDate;

    public Rental(long userId, long exemplarId, LocalDateTime rentDate, LocalDateTime returnDate) {
        this.userId = userId;
        this.exemplarId = exemplarId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}

package com.binar.challenge5.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
    @NonNull
    private Integer idUser;
    @NonNull
    private Integer idFilm;
    @NonNull
    private String showDate;
    @NonNull
    private String startingHour;
    @NonNull
    private Character studioName;
    @NonNull
    private Integer seatNo;
}
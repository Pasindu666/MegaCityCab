package com.pasindu.MegaCityCab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pasindu.MegaCityCab.entity.Car;
import com.pasindu.MegaCityCab.entity.User;

import lombok.Data;

import java.time.LocalDate;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)


public class BookingDTO {

    private Long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfPassengers;
    private String confirmationCode;
    private UserDTO user;
    private CarDTO car;

}

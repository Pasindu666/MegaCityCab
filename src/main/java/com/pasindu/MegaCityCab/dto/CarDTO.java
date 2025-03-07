package com.pasindu.MegaCityCab.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.pasindu.MegaCityCab.entity.Booking;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CarDTO {

    private Long id;
    private String carType;
    private String carBrand;
    private BigDecimal carPrice;
    private String carPhotoUrl;
    private String carColor;
    private String carDescription;
    private List<BookingDTO> bookings;
}

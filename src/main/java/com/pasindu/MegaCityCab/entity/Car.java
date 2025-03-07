package com.pasindu.MegaCityCab.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cars")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carType;
    private String carBrand;
    private BigDecimal carPrice;
    private String carPhotoUrl;
    private String carColor;
    private String carDescription;
    private List<Booking> bookings = new ArrayList<>();

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carType='" + carType + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carPrice=" + carPrice +
                ", carPhotoUrl='" + carPhotoUrl + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carDescription='" + carDescription + '\'' +
                '}';
    }
}

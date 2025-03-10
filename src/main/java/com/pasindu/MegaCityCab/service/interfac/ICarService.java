package com.pasindu.MegaCityCab.service.interfac;

import com.pasindu.MegaCityCab.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ICarService {

    Response addNewCar(MultipartFile photo, String carType, BigDecimal carPrice, String description);

    List<String> getAllCarTypes();

    Response getAllCars();

    Response deleteCar(Long carId);

    Response updateCar(Long carId, String description, String carType, BigDecimal carPrice, MultipartFile photo);

    Response getCarById(Long carId);

    Response getAvailableCarsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String carType);

    Response getAllAvailableCars();
}
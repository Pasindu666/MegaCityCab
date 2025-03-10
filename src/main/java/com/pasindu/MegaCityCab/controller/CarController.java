package com.pasindu.MegaCityCab.controller;

import com.pasindu.MegaCityCab.dto.Response;
import com.pasindu.MegaCityCab.service.interfac.IBookingService;
import com.pasindu.MegaCityCab.service.interfac.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private ICarService carService;
    @Autowired
    private IBookingService iBookingService;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewCar(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "carType", required = false) String carType,
            @RequestParam(value = "carPrice", required = false) BigDecimal carPrice,
            @RequestParam(value = "carDescription", required = false) String carDescription
    ) {

        if (photo == null || photo.isEmpty() || carType == null || carType.isBlank() || carPrice == null || carType.isBlank()) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo, carType,carPrice)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = carService.addNewCar(photo, carType, carPrice, carDescription);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllCars() {
        Response response = carService.getAllCars();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/types")
    public List<String> getCarTypes() {
        return carService.getAllCarTypes();
    }

    @GetMapping("/car-by-id/{carId}")
    public ResponseEntity<Response> getCarById(@PathVariable Long carId) {
        Response response = carService.getCarById(carId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-available-cars")
    public ResponseEntity<Response> getAvailableCars() {
        Response response = carService.getAllAvailableCars();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/available-cars-by-date-and-type")
    public ResponseEntity<Response> getAvailableCarsByDateAndType(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam(required = false) String carType
    ) {
        if (checkInDate == null || carType == null || carType.isBlank() || checkOutDate == null) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(checkInDate, carType,checkOutDate)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = carService.getAvailableCarsByDataAndType(checkInDate, checkOutDate, carType);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{carId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateCar(@PathVariable Long carId,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo,
                                               @RequestParam(value = "carType", required = false) String carType,
                                               @RequestParam(value = "carPrice", required = false) BigDecimal carPrice,
                                               @RequestParam(value = "carDescription", required = false) String carDescription

    ) {
        Response response = carService.updateCar(carId, carDescription, carType, carPrice, photo);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{carId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteCar(@PathVariable Long carId) {
        Response response = carService.deleteCar(carId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }


}
package com.pasindu.MegaCityCab.service.impl;

import com.pasindu.MegaCityCab.dto.Response;
import com.pasindu.MegaCityCab.dto.CarDTO;
import com.pasindu.MegaCityCab.entity.Car;
import com.pasindu.MegaCityCab.exception.OurException;
import com.pasindu.MegaCityCab.repo.BookingRepository;
import com.pasindu.MegaCityCab.repo.CarRepository;
import com.pasindu.MegaCityCab.service.AwsS3Service;
import com.pasindu.MegaCityCab.service.interfac.ICarService;
import com.pasindu.MegaCityCab.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CarService implements ICarService {


    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AwsS3Service awsS3Service;

    @Override
    public Response addNewCar(MultipartFile photo, String carType, BigDecimal carPrice, String description) {
        Response response = new Response();

        try {
            String imageUrl = awsS3Service.saveImageToS3(photo);
            Car car = new Car();
            car.setCarPhotoUrl(imageUrl);
            car.setCarType(carType);
            car.setCarPrice(carPrice);
            car.setCarDescription(description);
            Car savedCar = carRepository.save(car);
            CarDTO carDTO = Utils.mapCarEntityToCarDTO(savedCar);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCar(carDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getAllCarTypes() {
        return carRepository.findDistinctCarTypes();
    }

    @Override
    public Response getAllCars() {
        Response response = new Response();

        try {
            List<Car> carList = carRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<CarDTO> carDTOList = Utils.mapCarListEntityToCarListDTO(carList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCarList(carDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteCar(Long carId) {
        Response response = new Response();

        try {
            carRepository.findById(carId).orElseThrow(() -> new OurException("Car Not Found"));
            carRepository.deleteById(carId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateCar(Long carId, String description, String carType, BigDecimal carPrice, MultipartFile photo) {
        Response response = new Response();

        try {
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()) {
                imageUrl = awsS3Service.saveImageToS3(photo);
            }
            Car car = carRepository.findById(carId).orElseThrow(() -> new OurException("Car Not Found"));
            if (carType != null) car.setCarType(carType);
            if (carPrice != null) car.setCarPrice(carPrice);
            if (description != null) car.setCarDescription(description);
            if (imageUrl != null) car.setCarPhotoUrl(imageUrl);

            Car updatedCar = carRepository.save(car);
            CarDTO carDTO = Utils.mapCarEntityToCarDTO(updatedCar);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCar(carDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getCarById(Long carId) {
        Response response = new Response();

        try {
            Car car = carRepository.findById(carId).orElseThrow(() -> new OurException("Car Not Found"));
            CarDTO carDTO = Utils.mapCarEntityToCarDTOPlusBookings(car);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCar(carDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAvailableCarsByDataAndType(LocalDate checkInDate, LocalDate checkOutDate, String carType) {
        Response response = new Response();

        try {
            List<Car> availableCars = carRepository.findAvailableCarsByDatesAndTypes(checkInDate, checkOutDate, carType);
            List<CarDTO> carDTOList = Utils.mapCarListEntityToCarListDTO(availableCars);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCarList(carDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllAvailableCars() {
        Response response = new Response();

        try {
            List<Car> carList = carRepository.getAllAvailableCars();
            List<CarDTO> carDTOList = Utils.mapCarListEntityToCarListDTO(carList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setCarList(carDTOList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a car " + e.getMessage());
        }
        return response;
    }
}
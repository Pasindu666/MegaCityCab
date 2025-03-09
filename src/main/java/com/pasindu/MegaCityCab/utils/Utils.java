package com.pasindu.MegaCityCab.utils;

import com.pasindu.MegaCityCab.dto.BookingDTO;
import com.pasindu.MegaCityCab.dto.CarDTO;
import com.pasindu.MegaCityCab.dto.UserDTO;
import com.pasindu.MegaCityCab.entity.Booking;
import com.pasindu.MegaCityCab.entity.Car;
import com.pasindu.MegaCityCab.entity.User;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();


    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }


    public static UserDTO mapUserEntityToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static CarDTO mapCarEntityToCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setCarType(car.getCarType());
        carDTO.setCarPrice(car.getCarPrice());
        carDTO.setCarPhotoUrl(car.getCarPhotoUrl());
        carDTO.setCarDescription(car.getCarDescription());
        return carDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        // Map simple fields
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumberOfPassengers(booking.getNumberOfPassengers());
        bookingDTO.setConfirmationCode(booking.getConfirmationCode());
        return bookingDTO;
    }

    public static CarDTO mapCarEntityToCarDTOPlusBookings(Car car) {
        CarDTO carDTO = new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setCarType(car.getCarType());
        carDTO.setCarPrice(car.getCarPrice());
        carDTO.setCarPhotoUrl(car.getCarPhotoUrl());
        carDTO.setCarDescription(car.getCarDescription());

        if (car.getBookings() != null) {
            carDTO.setBookings(car.getBookings().stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList()));
        }
        return carDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTOPlusBookedCars(Booking booking, boolean mapUser) {

        BookingDTO bookingDTO = new BookingDTO();
        // Map simple fields
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumberOfPassengers(booking.getNumberOfPassengers());
        bookingDTO.setConfirmationCode(booking.getConfirmationCode());
        if (mapUser) {
            bookingDTO.setUser(Utils.mapUserEntityToUserDTO(booking.getUser()));
        }
        if (booking.getCar() != null) {
            CarDTO carDTO = new CarDTO();

            carDTO.setId(booking.getCar().getId());
            carDTO.setCarType(booking.getCar().getCarType());
            carDTO.setCarPrice(booking.getCar().getCarPrice());
            carDTO.setCarPhotoUrl(booking.getCar().getCarPhotoUrl());
            carDTO.setCarDescription(booking.getCar().getCarDescription());
            bookingDTO.setCar(carDTO);
        }
        return bookingDTO;
    }

    public static UserDTO mapUserEntityToUserDTOPlusUserBookingsAndCar(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()) {
            userDTO.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingDTOPlusBookedCars(booking, false)).collect(Collectors.toList()));
        }
        return userDTO;
    }


    public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {
        return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }

    public static List<CarDTO> mapCarListEntityToCarListDTO(List<Car> carList) {
        return carList.stream().map(Utils::mapCarEntityToCarDTO).collect(Collectors.toList());
    }

    public static List<BookingDTO> mapBookingListEntityToBookingListDTO(List<Booking> bookingList) {
        return bookingList.stream().map(Utils::mapBookingEntityToBookingDTO).collect(Collectors.toList());
    }


}
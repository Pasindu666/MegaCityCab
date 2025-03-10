package com.pasindu.MegaCityCab.service.interfac;

import com.pasindu.MegaCityCab.dto.Response;
import com.pasindu.MegaCityCab.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long carId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
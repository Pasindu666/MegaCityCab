package com.pasindu.MegaCityCab.repo;

import com.pasindu.MegaCityCab.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCarId(Long carId);

    List<Booking> findByConfirmationCode(String confirmationCode);

    List<Booking> findByUserId(Long userId);

}

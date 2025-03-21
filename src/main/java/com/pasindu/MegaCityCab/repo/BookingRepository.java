package com.pasindu.MegaCityCab.repo;

import com.pasindu.MegaCityCab.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCarId(Long carId);

    Optional<Booking> findByConfirmationCode(String confirmationCode);


    List<Booking> findByUserId(Long userId);

}

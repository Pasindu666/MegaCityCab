package com.pasindu.MegaCityCab.repo;

import com.pasindu.MegaCityCab.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT DISTINCT c.carType from  Car c")
    List<String> findDistinctCarTypes();

    @Query("SELECT c from Car c WHERE c.carType LIKE %:roomType% AND c.id NOT IN (SELECT bk.car.id FROM Booking bk WHERE "+"" +
            "(bk.checkInDate <= :checkOutDate) AND  (bk.checkOutDate >= :checkInDate))")
    List<Car> findAvailableCarsByDatesAndTypes(LocalDate CheckInDate, LocalDate CheckOutDate, String carType);

    @Query("SELECT c FROM Car c WHERE c.id NOT IN (SELECT b.car.id FROM Booking b)")
    List<Car> getAllAvailableCars();


}

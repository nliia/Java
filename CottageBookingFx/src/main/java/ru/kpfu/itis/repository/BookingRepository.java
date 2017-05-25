package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}

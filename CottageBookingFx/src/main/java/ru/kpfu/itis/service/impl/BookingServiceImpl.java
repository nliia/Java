package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.Booking;
import ru.kpfu.itis.repository.BookingRepository;
import ru.kpfu.itis.service.BookingService;

import java.util.List;

/**
 * @author Liia
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    @Autowired
    BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void add(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.delete(id);
    }

    @Override
    public void update(Booking booking) {
        bookingRepository.delete(booking.getId());
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }
}

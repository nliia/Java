package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Booking;

import java.util.List;

/**
 * @author Liia
 */
public interface BookingService {
    public void add(Booking booking);

    public void delete(Long id);

    public void update(Booking booking);

    public List<Booking> getAll();
}

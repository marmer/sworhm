package io.github.marmer.sworhm.core;

import io.github.marmer.sworhm.core.model.Booking;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Service to handle bookings.
 */
public interface BookingService {

    /**
     * Loads all bookings for a specific day.
     *
     * @param day The day to load Bookings for.
     * @return Bookings of the day
     */
    Stream<Booking> getBookingsByDay(LocalDate day);

    /**
     * Stores the given booking or if a booking with the given id exists already it will be updated.
     *
     * @param booking Booking to store
     */
    void saveOrUpdate(Booking booking);

    void deleteBookingById(String id);
}

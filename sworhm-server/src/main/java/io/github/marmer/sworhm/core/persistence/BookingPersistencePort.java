package io.github.marmer.sworhm.core.persistence;

import io.github.marmer.sworhm.core.model.Booking;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * Port to access persistence concerns for Bookings.
 */
public interface BookingPersistencePort {
    /**
     * Loads all bookings for a specific day.
     *
     * @param day The day to load Bookings for.
     * @return Bookings of the day
     */
    Stream<Booking> findBookingsByDay(LocalDate day);

    void saveOrUpdate(Booking booking);

    void deleteBookingById(String id);
}

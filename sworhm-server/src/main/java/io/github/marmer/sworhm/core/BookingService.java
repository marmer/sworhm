package io.github.marmer.sworhm.core;

import io.github.marmer.sworhm.core.model.Booking;

import java.time.LocalDate;
import java.util.List;

/**
 * Service to handle bookings.
 */
public interface BookingService {
    /**
     * Persists or updates a booking.
     *
     * @param booking The booking to persist.
     * @return The persisted booking.
     */
    Booking storeBooking(Booking booking);

    /**
     * Loads all bookings for a specific day.
     *
     * @param day The day to load Bookings for.
     * @return Bookings of the day
     */
    List<Booking> findBookingsByDay(LocalDate day);
}

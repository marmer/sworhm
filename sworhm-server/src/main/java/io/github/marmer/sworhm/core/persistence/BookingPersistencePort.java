package io.github.marmer.sworhm.core.persistence;

import io.github.marmer.sworhm.core.model.Booking;

import java.time.LocalDate;
import java.util.List;

/**
 * Port to access persistence concerns for Bookings.
 */
public interface BookingPersistencePort {
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

    /**
     * Deletes a booking by its ID.
     *
     * @param bookingId the id of the booking to delete.
     */
    void deleteBooking(String bookingId);
}

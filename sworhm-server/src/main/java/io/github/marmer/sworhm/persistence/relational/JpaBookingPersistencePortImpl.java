package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
import io.github.marmer.sworhm.persistence.relational.converter.BookingConverter;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingEntityRepository;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Named
public class JpaBookingPersistencePortImpl implements BookingPersistencePort {
    private final BookingEntityRepository bookingEntityRepository;
    private final BookingConverter bookingConverter;

    public JpaBookingPersistencePortImpl(final BookingEntityRepository bookingEntityRepository, final BookingConverter bookingConverter) {
        this.bookingEntityRepository = bookingEntityRepository;
        this.bookingConverter = bookingConverter;
    }

    @Override
    public Booking storeBooking(final Booking booking) {
        // TODO: marmer 06.01.2019 implement me
        return Booking.builder().startTime(LocalTime.now()).build();
    }

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        return bookingConverter.convert(bookingEntityRepository.findByDayDay(day));
    }
}

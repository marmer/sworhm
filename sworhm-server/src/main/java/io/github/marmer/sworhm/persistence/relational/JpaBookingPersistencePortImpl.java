package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
import io.github.marmer.sworhm.persistence.relational.converter.entity.BookingEntityConverter;
import io.github.marmer.sworhm.persistence.relational.converter.internal.BookingConverterFromEntity;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDayEntityRepository;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingEntityRepository;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
public class JpaBookingPersistencePortImpl implements BookingPersistencePort {
    private final BookingEntityRepository bookingEntityRepository;
    private final BookingConverterFromEntity bookingConverterFromEntity;
    private final BookingEntityConverter bookingEntityConverter;
    private final BookingDayEntityRepository bookingDayEntityRepository;

    public JpaBookingPersistencePortImpl(final BookingEntityRepository bookingEntityRepository, final BookingConverterFromEntity bookingConverterFromEntity, final BookingEntityConverter bookingEntityConverter, final BookingDayEntityRepository bookingDayEntityRepository) {
        this.bookingEntityRepository = bookingEntityRepository;
        this.bookingConverterFromEntity = bookingConverterFromEntity;
        this.bookingEntityConverter = bookingEntityConverter;
        this.bookingDayEntityRepository = bookingDayEntityRepository;
    }

    @Override
    public Booking storeBooking(final Booking booking) {
        final BookingEntity bookingToSave = bookingEntityConverter.convert(booking);
        bookingDayEntityRepository.findByDay(booking.getDay().getDay()).ifPresent(bookingToSave::setDay);
        final BookingEntity saved = bookingEntityRepository.save(bookingToSave);
        return bookingConverterFromEntity.convert(saved);
    }

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        return bookingConverterFromEntity.convert(bookingEntityRepository.findByDayDay(day));
    }

    @Override
    public void deleteBooking(final String bookingId) {
        bookingEntityRepository.deleteById(bookingId);
    }
}

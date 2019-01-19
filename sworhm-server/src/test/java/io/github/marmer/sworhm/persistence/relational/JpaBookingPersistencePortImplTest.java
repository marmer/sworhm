package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.persistence.relational.converter.entity.BookingEntityConverter;
import io.github.marmer.sworhm.persistence.relational.converter.internal.BookingConverterFromEntity;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntity;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDayEntityRepository;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaBookingPersistencePortImplTest {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @Mock
    private BookingEntityRepository bookingEntityRepository;
    @Mock
    private BookingConverterFromEntity bookingConverterFromEntity;
    @InjectMocks
    private JpaBookingPersistencePortImpl underTest;
    @Mock
    private BookingEntityConverter bookingEntityConverter;
    @Mock
    private BookingDayEntityRepository bookingDayEntityRepository;

    @Test
    @DisplayName("Should serve existing bookings")
    void testFindBookingsByDay_ShouldServeExistingBookings()
            throws Exception {
        // Preparation
        final LocalDate day = testdatageneratorPersistence.getRandom().nextObject(LocalDate.class);
        final BookingEntity bookingEntity = testdatageneratorPersistence.newBookingEntity().build();
        final Booking booking = testdatagenerator.newBooking().build();

        when(bookingEntityRepository.findByDayDay(day)).thenReturn(singletonList(bookingEntity));
        when(bookingConverterFromEntity.convert(singletonList(bookingEntity))).thenReturn(singletonList(booking));

        // Execution
        final var result = underTest.findBookingsByDay(day);

        // Assertion
        assertThat(result, contains(booking));
    }

    @Test
    @DisplayName("should store the given booking")
    void testStoreBooking_ShouldStoreTheGivenBooking()
            throws Exception {
        // Preparation
        final Booking bookingToStore = testdatagenerator.newBooking().build();
        final BookingEntity bookingEntityToStore = testdatageneratorPersistence.newBookingEntity().build();
        final BookingEntity storedBookingEntity = testdatageneratorPersistence.newBookingEntity().build();
        final Booking storedBooking = testdatagenerator.newBooking().build();

        when(bookingEntityConverter.convert(bookingToStore)).thenReturn(bookingEntityToStore);
        when(bookingEntityRepository.save(bookingEntityToStore)).thenReturn(storedBookingEntity);
        when(bookingConverterFromEntity.convert(storedBookingEntity)).thenReturn(storedBooking);


        // Execution
        final var result = underTest.storeBooking(bookingToStore);

        // Assertion
        assertThat(result, is(storedBooking));
    }

    @Test
    @DisplayName("should reuse old booking days")
    void testStoreBooking_ShouldReuseOldBookingDays()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2017, 8, 9);
        final Booking bookingToStore = testdatagenerator.newBooking()
                .day(testdatagenerator.newBookingDay()
                        .day(day)
                        .build())
                .build();
        final BookingEntity.BookingEntityBuilder bookingEntityToStoreTemplate = testdatageneratorPersistence.newBookingEntity();
        final BookingEntity bookingEntityToStore = bookingEntityToStoreTemplate.build();
        final BookingEntity storedBookingEntity = testdatageneratorPersistence.newBookingEntity().build();
        final Booking storedBooking = testdatagenerator.newBooking().build();
        final BookingDayEntity oldBookingDay = testdatageneratorPersistence.newBookingDayEntity().build();

        when(bookingEntityConverter.convert(bookingToStore)).thenReturn(bookingEntityToStore);
        when(bookingDayEntityRepository.findByDay(day)).thenReturn(Optional.of(oldBookingDay));
        when(bookingEntityRepository.save(bookingEntityToStoreTemplate.day(oldBookingDay).build())).thenReturn(storedBookingEntity);
        when(bookingConverterFromEntity.convert(storedBookingEntity)).thenReturn(storedBooking);

        // Execution
        final var result = underTest.storeBooking(bookingToStore);

        // Assertion
        assertThat(result, is(storedBooking));
    }

    @Test
    @DisplayName("should delete existing booking")
    void testDeleteBooking_ShouldDeleteExistingBooking()
            throws Exception {
        // Preparation
        final String bookingId = "0815";

        // Execution
        underTest.deleteBooking(bookingId);

        // Assertion
        verify(bookingEntityRepository).deleteById(bookingId);
    }
}
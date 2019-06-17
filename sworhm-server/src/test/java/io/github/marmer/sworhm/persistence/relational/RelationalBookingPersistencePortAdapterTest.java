package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.persistence.RelationalBookingPersistencePortAdapter;
import io.github.marmer.sworhm.persistence.relational.converter.external.BookingDboFromBookingConverter;
import io.github.marmer.sworhm.persistence.relational.converter.internal.BookingFromBookingDboConverter;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDboRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static co.unruly.matchers.StreamMatchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelationalBookingPersistencePortAdapterTest {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @InjectMocks
    private RelationalBookingPersistencePortAdapter underTest;
    @Mock
    private BookingDboRepository bookingDboRepository;
    @Mock
    private BookingFromBookingDboConverter bookingFromBookingDboConverter;
    @Mock
    private BookingDboFromBookingConverter bookingDboFromBookingConverter;

    @Test
    @DisplayName("Existing bookings should be returned")
    void findBookingsByDay_ExistingBookingsShouldBeReturned()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2000, 3, 4);
        final BookingDbo bookingDbo = testdatageneratorPersistence.newBookingDbo();
        final Booking booking = testdatagenerator.newBooking().build();

        when(bookingDboRepository.findAllByDay(day)).thenReturn(List.of(bookingDbo));
        when(bookingFromBookingDboConverter.convert(bookingDbo)).thenReturn(booking);

        // Execution
        final var result = underTest.findBookingsByDay(day);

        // Assertion
        assertThat(result, contains(booking));
    }

    @Test
    @DisplayName("Given booking should be stored or updated")
    void saveOrUpdate_GivenBookingShouldBeStoredOrUpdated()
            throws Exception {
        // Preparation
        final Booking booking = testdatagenerator.newBooking().build();
        final BookingDbo bookingDbo = testdatageneratorPersistence.newBookingDbo();
        when(bookingDboFromBookingConverter.convert(booking)).thenReturn(bookingDbo);

        // Execution
        underTest.saveOrUpdate(booking);

        // Assertion
        verify(bookingDboRepository).saveOrUpdate(bookingDbo);
    }
}
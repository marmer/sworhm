package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
import io.github.marmer.sworhm.model.Testdatagenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static co.unruly.matchers.StreamMatchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleBookingServiceTest {
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @InjectMocks
    private SimpleBookingService underTest;
    @Mock
    private BookingPersistencePort bookingPersistencePort;

    @Test
    @DisplayName("Existing bookings should bereturned")
    void getBookingsByDay_ExistingBookingsShouldBereturned()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2020, 12, 21);
        final Booking booking = testdatagenerator.newBooking().build();
        when(bookingPersistencePort.findBookingsByDay(day)).thenReturn(Stream.of(booking));

        // Execution
        final var result = underTest.getBookingsByDay(day);

        // Assertion
        assertThat(result, contains(booking));
    }

    @Test
    @DisplayName("Given booking should be stored or updated")
    void saveOrUpdate_GivenBookingShouldBeStoredOrUpdated()
            throws Exception {
        // Preparation
        final Booking booking = testdatagenerator.newBooking().build();

        // Execution
        underTest.saveOrUpdate(booking);

        // Assertion
        verify(bookingPersistencePort).saveOrUpdate(booking);
    }
}
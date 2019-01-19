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
import java.time.Month;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleBookingServiceTest {
    @RegisterExtension
    final Testdatagenerator testdata = new Testdatagenerator();

    @InjectMocks
    private SimpleBookingService underTest;

    @Mock
    private BookingPersistencePort bookingPersistencePort;

    @Test
    @DisplayName("Should return persisted version of the persisted booking")
    public void testStoreBooking_BookingGiven_ShouldServePersistedVersion()
            throws Exception {
        // Preparation
        final Booking booking = testdata.newBooking().build();
        final Booking storedBooking = testdata.newBooking().build();
        when(bookingPersistencePort.storeBooking(booking)).thenReturn(storedBooking);

        // Execution
        final Booking result = underTest.storeBooking(booking);

        // Assertion
        assertThat(result, is(storedBooking));
    }

    @Test
    @DisplayName("Should return existing booking")
    public void testFindBookingsByDay_BookingsExistForDay_ShouldReturnBookings()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(1985, Month.JANUARY, 2);
        final Booking booking = testdata.newBooking().build();
        when(bookingPersistencePort.findBookingsByDay(day)).thenReturn(singletonList(booking));

        // Execution
        final List<Booking> result = underTest.findBookingsByDay(day);

        // Assertion
        assertThat(result, contains(booking));
    }

    @Test
    @DisplayName("Shuold delete booking")
    void testDeleteBooking_ShuoldDeleteBooking()
            throws Exception {
        // Preparation
        final String bookingId = "42";

        // Execution
        underTest.deleteBooking(bookingId);

        // Assertion
        verify(bookingPersistencePort).deleteBooking(bookingId);
    }

}
package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.model.Testdatagenerator;
import io.github.marmer.sworhm.core.persistence.BookingPersistencePort;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleBookingServiceTest {
    @RegisterExtension
    private final Testdatagenerator testdata = new Testdatagenerator();

    @InjectMocks
    private SimpleBookingService underTest;

    @Mock
    private BookingPersistencePort bookingPersistencePort;
    @Test
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
}
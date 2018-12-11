package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.SystemTimeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @InjectMocks
    private BookingController underTest;
    @Mock
    private SystemTimeService systemTimeService;

    @Test
    @DisplayName("Get request for any bookings should redirect to todays bookings date")
    void testGetDefaultBookingPage_GetRequestForAnyBookingsShouldRedirectToTodaysBookings()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2002, 2, 20, 1, 2);
        when(systemTimeService.now()).thenReturn(now);

        // Execution
        final String result = underTest.getDefaultBookingPage();

        // Assertion
        assertThat(result, is("redirect:/day/:2002-02-20/bookings"));
    }

    @Test
    @DisplayName("Get request for todays bookings should redirect to todays bookings date")
    void testGetTodaysBookingPage_GetRequestForAnyBookingsShouldRedirectToTodaysBookings()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2002, 2, 20, 1, 2);
        when(systemTimeService.now()).thenReturn(now);

        // Execution
        final String result = underTest.getTodaysBookingPage();

        // Assertion
        assertThat(result, is("redirect:/day/:2002-02-20/bookings"));
    }

}
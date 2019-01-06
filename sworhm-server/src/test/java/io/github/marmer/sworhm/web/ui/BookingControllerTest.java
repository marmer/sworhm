package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.web.ui.converter.BookingDTOConverter;
import io.github.marmer.sworhm.web.ui.dto.TestdatageneratorWebUiDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static io.github.marmer.sworhm.web.ModelAndViewMatcher.isModelAndView;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @RegisterExtension
    private final TestdatageneratorWebUiDTO testdatageneratorWebDTO = new TestdatageneratorWebUiDTO();
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @InjectMocks
    private BookingController underTest;
    @Mock
    private SystemTimeService systemTimeService;
    @Mock
    private BookingService bookingService;
    @Mock
    private BookingDTOConverter bookingDTOConverter;

    @Test
    @DisplayName("Get request for any bookings should redirect to todays bookings date")
    void testGetDefaultBookingPage_GetRequestForAnyBookingsShouldRedirectToTodaysBookings()
            throws Exception {
        // Execution
        final String result = underTest.getDefaultBookingPage();

        // Assertion
        assertThat(result, is("redirect:/day/:today/bookings"));
    }

    @Test
    @DisplayName("Todays existing bookings schould be shown")
    void testGetTodaysBookingPage_ExistingBookingsSchouldBeShown()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2002, 2, 20, 1, 2);
        when(systemTimeService.now()).thenReturn(now);
        final Booking booking = testdatagenerator.newBooking().build();
        when(bookingService.findBookingsByDay(LocalDate.of(2002, 2, 20))).thenReturn(singletonList(booking));
        final BookingController.BookingDTO bookingDTO = testdatageneratorWebDTO.newBookingDTO();
        when(bookingDTOConverter.convert(singletonList(booking))).thenReturn(singletonList(bookingDTO));

        // Execution
        final var result = underTest.getTodaysBookingPage();
        final Map<String, Object> model = result.getModel();

        // Assertion
        assertThat(result, isModelAndView()
                .withViewName("bookings")
                .withModel(hasEntry(is("bookings"), contains(bookingDTO))));
    }

    @Test
    @DisplayName("Bookings for therequested day should be shown")
    void testGetBookingsForDay_BookingsForTherequestedDayShouldBeShown()
            throws Exception {
        // Preparation
        final Booking booking = testdatagenerator.newBooking().build();
        final LocalDate day = LocalDate.of(2000, 5, 6);
        when(bookingService.findBookingsByDay(day)).thenReturn(singletonList(booking));
        final BookingController.BookingDTO bookingDTO = testdatageneratorWebDTO.newBookingDTO();
        when(bookingDTOConverter.convert(singletonList(booking))).thenReturn(singletonList(bookingDTO));

        // Execution
        final var result = underTest.getBookingsForDay(day);

        // Assertion
        final Map<String, Object> model = result.getModel();

        // Assertion
        assertThat(result, isModelAndView()
                .withViewName("bookings")
                .withModel(hasEntry(is("bookings"), contains(bookingDTO))));
    }

}
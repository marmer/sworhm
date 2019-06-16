package io.github.marmer.sworhm.rest.v1;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.rest.v1.converter.external.BookingDtoFromModelConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static io.github.marmer.sworhm.rest.v1.BookingControllerMatcher.BookingsDtoMatcher.isBookingsDto;
import static java.time.LocalDate.now;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @RegisterExtension
    private final TestdatageneratorRest testdatageneratorRest = new TestdatageneratorRest();
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @InjectMocks
    private BookingController underTest;
    @Mock
    private BookingService bookingService;
    @Mock
    private BookingDtoFromModelConverter bookingDtoFromModelConverter;

    @Test
    @DisplayName("Should return the existing bookings")
    void getBookings_ShouldReturnTheExistingBookings()
            throws Exception {
        // Preparation
        final LocalDate day = now();
        final Booking booking = testdatagenerator.newBooking().build();
        final BookingController.BookingDto bookingDto = testdatageneratorRest.newBookingDto();

        when(bookingService.getBookingsByDay(day)).thenReturn(Stream.of(booking));
        when(bookingDtoFromModelConverter.convert(booking)).thenReturn(bookingDto);

        // Execution
        final var result = underTest.getBookingsByDay(day);

        // Assertion
        assertThat(result, isBookingsDto()
                .withDay(day)
                .withBookings(contains(bookingDto)));

    }
}
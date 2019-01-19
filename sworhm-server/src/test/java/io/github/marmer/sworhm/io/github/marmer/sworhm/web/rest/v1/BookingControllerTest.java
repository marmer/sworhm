package io.github.marmer.sworhm.io.github.marmer.sworhm.web.rest.v1;

import io.github.marmer.sworhm.core.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.http.ResponseEntityMatcher.isResponseEntity;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @InjectMocks
    private BookingController underTest;

    @Mock
    private BookingService bookingService;

    @Test
    @DisplayName("Should delete Booking by id and day")
    void testDeleteBooking_ShouldDeleteBookingByIdAndDay()
            throws Exception {
        // Preparation
        final LocalDate anyDay = LocalDate.of(2018, 1, 2);
        final String bookingId = "123";


        // Execution
        final var result = underTest.deleteBooking(anyDay, bookingId);

        // Assertion
        assertThat(result, isResponseEntity()
                .withStatusCode(HttpStatus.NO_CONTENT));
        verify(bookingService).deleteBooking(bookingId);
    }
}
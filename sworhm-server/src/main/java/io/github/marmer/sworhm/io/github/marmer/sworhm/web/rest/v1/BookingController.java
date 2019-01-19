package io.github.marmer.sworhm.io.github.marmer.sworhm.web.rest.v1;

import io.github.marmer.sworhm.core.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController("v1RestBookingController")
@RequestMapping("/api/v1/days")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @DeleteMapping("/:{day}/bookings/{bookingId}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate day,
            @PathVariable final String bookingId) {

        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}

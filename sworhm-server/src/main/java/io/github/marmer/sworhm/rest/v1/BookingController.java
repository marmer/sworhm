package io.github.marmer.sworhm.rest.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.Converter;
import io.github.marmer.sworhm.core.model.Booking;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/days/{bookingDay}/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final Converter<Booking, BookingDto> bookingConverter;

    public BookingController(final BookingService bookingService, final Converter<Booking, BookingDto> bookingConverter) {
        this.bookingService = bookingService;
        this.bookingConverter = bookingConverter;
    }

    @GetMapping
    public BookingsDto getBookingsByDay(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate bookingDay) {

        return new BookingsDto()
                .setDay(bookingDay)
                .setBookings(bookingService.getBookingsByDay(bookingDay)
                        .map(bookingConverter::convert)
                        .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public void putBooking(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate bookingDay,
                           @RequestBody final BookingDto booking) {
    }

    @Data
    public static class BookingsDto {
        private LocalDate day;
        private List<BookingDto> bookings = new ArrayList<>();
    }

    @Data
    public static class BookingDto {
        private String id;
        @JsonFormat(pattern = "HH:mm")
        private LocalTime startTime;
        private int durationInMinutes;
        private String description;
        private String ticket;
        private String notes;
    }
}

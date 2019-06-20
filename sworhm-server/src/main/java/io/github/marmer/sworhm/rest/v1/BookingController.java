package io.github.marmer.sworhm.rest.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.rest.v1.converter.external.BookingDtoFromModelConverter;
import io.github.marmer.sworhm.rest.v1.converter.internal.BookingFromDtoConverter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/days/{bookingDay}/bookings")
@CrossOrigin
public class BookingController {
    private final BookingService bookingService;
    private final BookingFromDtoConverter bookingFromDtoConverter;
    private final BookingDtoFromModelConverter bookingDtoFromModelConverter;

    public BookingController(final BookingService bookingService, final BookingFromDtoConverter bookingFromDtoConverter, final BookingDtoFromModelConverter bookingDtoFromModelConverter) {
        this.bookingService = bookingService;
        this.bookingFromDtoConverter = bookingFromDtoConverter;
        this.bookingDtoFromModelConverter = bookingDtoFromModelConverter;
    }

    @GetMapping
    public BookingsDto getBookingsByDay(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate bookingDay) {

        return new BookingsDto()
                .setDay(bookingDay)
                .setBookings(bookingsAtDay(bookingDay));
    }

    private List<BookingDto> bookingsAtDay(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable final LocalDate bookingDay) {
        return bookingService.getBookingsByDay(bookingDay)
                .map(bookingDtoFromModelConverter::convert)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void putBooking(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate bookingDay,
                           @RequestBody final BookingDto bookingDto) {
        final Booking booking = bookingFromDtoConverter.convert(bookingDto);
        bookingService.saveOrUpdate(booking.withDay(bookingDay));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookingById(final String id) {
        bookingService.deleteBookingById(id);
    }

    @Data
    public static class BookingsDto {
        private LocalDate day;
        private List<BookingDto> bookings = new ArrayList<>();
    }

    @Data
    public static class BookingDto {
        @NotBlank
        private String id;
        @JsonFormat(pattern = "HH:mm")
        private LocalTime startTime;
        private int durationInMinutes;
        private String description;
        private String ticket;
        private String notes;
    }
}

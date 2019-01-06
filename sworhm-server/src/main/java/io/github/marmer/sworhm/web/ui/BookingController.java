package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.converter.BookingDTOConverter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping
public class BookingController {
    private final SystemTimeService systemTimeService;
    private final BookingDTOConverter bookingDTOConverter;
    private final BookingService bookingService;

    public BookingController(final SystemTimeService systemTimeService, final BookingDTOConverter bookingDTOConverter, final BookingService bookingService) {
        this.systemTimeService = systemTimeService;
        this.bookingDTOConverter = bookingDTOConverter;
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    String getDefaultBookingPage() {
        return "redirect:/day/:today/bookings";
    }

    @GetMapping("/day/:today/bookings")
    ModelAndView getTodaysBookingPage() {
        final LocalDate today = systemTimeService.now().toLocalDate();
        return getBookingsForDay(today);
    }

    @GetMapping("/day/:{day}/bookings")
    ModelAndView getBookingsForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        final List<Booking> bookings = bookingService.findBookingsByDay(day);
        final List<BookingDTO> dtos = bookingDTOConverter.convert(bookings);
        return new ModelAndView("bookings", "bookings", dtos);
    }

    @Data
    public static class BookingDTO {
        private String id;
        private String description;
        private LocalTime startTime;
    }
}

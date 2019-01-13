package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.converter.BookingDTOConverter;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final Map<String, Object> model = new HashMap<>();
        model.put("bookings", dtos);
        model.put("currentDay", day);
        return new ModelAndView("bookings", model);
    }

    @PostMapping("/day/:{pathDay}/bookings")
    ModelAndView addPooking(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate pathDay, @ModelAttribute final BookingDTO dto) {
        // TODO: marmer 13.01.2019 convert, replace day by day of path, save and show site again ;)
        return getBookingsForDay(pathDay);
    }
}

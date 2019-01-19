package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.converter.dto.BookingDTOConverter;
import io.github.marmer.sworhm.web.ui.converter.internal.BookingConverterFromDTO;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class BookingController {
    private final SystemTimeService systemTimeService;
    private final BookingDTOConverter bookingDTOConverter;
    private final BookingConverterFromDTO bookingConverterFromDTO;
    private final BookingService bookingService;

    public BookingController(final SystemTimeService systemTimeService, final BookingDTOConverter bookingDTOConverter, final BookingConverterFromDTO bookingConverterFromDTO, final BookingService bookingService) {
        this.systemTimeService = systemTimeService;
        this.bookingDTOConverter = bookingDTOConverter;
        this.bookingConverterFromDTO = bookingConverterFromDTO;
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    String getDefaultBookingPage() {
        return "redirect:/days/:today/bookings";
    }

    @GetMapping("/days/:today/bookings")
    ModelAndView getTodaysBookingPage() {
        final LocalDate today = systemTimeService.now().toLocalDate();
        return getBookingsForDay(today);
    }

    @GetMapping("/days/:{day}/bookings")
    ModelAndView getBookingsForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        final List<Booking> bookings = bookingService.findBookingsByDay(day);
        final List<BookingDTO> dtos = bookings.stream().map(bookingDTOConverter::convert).collect(Collectors.toList());
        final Map<String, Object> model = new HashMap<>();
        model.put("bookings", dtos);
        model.put("currentDay", day);
        return new ModelAndView("bookings", model);
    }

    @PostMapping("/days/:{pathDay}/bookings")
    ModelAndView addBooking(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate pathDay, @ModelAttribute final BookingDTO dto) {
        final Booking booking = bookingConverterFromDTO.convert(dto, pathDay);
        bookingService.storeBooking(booking);
        return getBookingsForDay(pathDay);
    }
}

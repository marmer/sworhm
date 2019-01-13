package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.web.ui.converter.BookingDTOConverter;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
@RequestMapping
public class BookingController {
    private final SystemTimeService systemTimeService;
    private final BookingDTOConverter bookingDTOConverter;
    private final BookingService bookingService;

    /**
     * // TODO: marmer 13.01.2019 Delete me!
     */
    private final List<BookingDTO> bookings = new ArrayList<>();

    public BookingController(final SystemTimeService systemTimeService, final BookingDTOConverter bookingDTOConverter, final BookingService bookingService) {
        this.systemTimeService = systemTimeService;
        this.bookingDTOConverter = bookingDTOConverter;
        this.bookingService = bookingService;

        bookings.add(oldBooking());
    }

    private BookingDTO oldBooking() {
        return new BookingDTO()
                .setId(UUID.randomUUID().toString())
                .setDay(LocalDate.of(1985, 1, 2))
                .setStartTime(LocalTime.of(15, 30))
                .setEndTime(LocalTime.of(15, 30))
                .setDuration(LocalTime.of(15, 30))
                .setNotes("oldNotes")
                .setTicket("oldTicket")
                .setDescription("description");
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

    // TODO: marmer 13.01.2019 revert
    @GetMapping("/day/:{day}/bookings")
    ModelAndView getBookingsForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
//        final List<Booking> bookings = bookingService.findBookingsByDay(day);
//        final List<BookingDTO> dtos = bookingDTOConverter.convert(bookings);
        final Map<String, Object> model = new HashMap<>();
        model.put("bookings", bookings);
        model.put("currentDay", day);
        return new ModelAndView("bookings", model);
    }

    // TODO: marmer 13.01.2019 fix
    @PostMapping("/day/:{pathDay}/bookings")
    ModelAndView addPooking(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate pathDay, @ModelAttribute final BookingDTO dto) {
        bookings.add(dto);
        System.out.println("####### " + pathDay);
        System.out.println("####### " + dto);
        return getBookingsForDay(pathDay);
    }
}

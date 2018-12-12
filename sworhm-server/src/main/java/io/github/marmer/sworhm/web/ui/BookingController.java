package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.SystemTimeService;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

@Controller
@RequestMapping
public class BookingController {
    private final SystemTimeService systemTimeService;

    public BookingController(final SystemTimeService systemTimeService) {
        this.systemTimeService = systemTimeService;
    }

    @GetMapping("/bookings")
    String getDefaultBookingPage() {
        return getTodaysBookingPage();
    }

    @GetMapping("/day/:today/bookings")
    String getTodaysBookingPage() {
        return "redirect:/day/:" + systemTimeService.now().toLocalDate() + "/bookings";
    }

    @GetMapping("/day/:{day}/bookings")
    ModelAndView getBookingsForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        // TODO: marmer 10.12.2018 you know what todo ;)
        final List<BookingDTO> bookings = asList(new BookingDTO().setDescription("first"),
                new BookingDTO().setDescription("second"),
                new BookingDTO().setDescription("third"));
        return new ModelAndView("bookings", "bookings", bookings);
    }

    @Data
    public static class BookingDTO {
        private String description;
    }
}

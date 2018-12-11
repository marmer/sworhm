package io.github.marmer.sworhm.web.ui;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping
public class BookingController {
    @GetMapping("/bookings")
    String getDefaultBookingPage() {
        return "redirect:/day/:/bookings";
    }

    @GetMapping("/:{day}")
    String getBookingsForDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate day) {
        // TODO: marmer 10.12.2018 you know what todo ;)
        return "bookings";
    }
}

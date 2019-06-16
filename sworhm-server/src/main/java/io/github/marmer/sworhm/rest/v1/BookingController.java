package io.github.marmer.sworhm.rest.v1;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/days/{bookingDay}/bookings")
public class BookingController {
    @GetMapping
    public BookingsDto getBookings(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate bookingDay) {
        return new BookingsDto()
                .setBookingDay("2002-02-01")
                .setBookings(List.of(
                        new BookingDto()
                                .setId("65b8818f-0320-450b-9da0-49f3269bafd7")
                                .setStartTime("00:55")
                                .setDurationInMinutes(45)
                                .setDescription("another one bites the dust")
                                .setTicket("JIRA-666")
                                .setNotes("knocking on heavens door"),
                        new BookingDto()
                                .setId("1412c9ec-4abe-44d2-a8af-406c45a55b54")
                                .setStartTime("01:55")
                                .setDurationInMinutes(117)
                                .setDescription("stay alive")
                                .setTicket("JIRA-999")
                                .setNotes("cheek to cheek")));
    }

    @Data
    public static class BookingsDto {
        private String bookingDay;
        private List<BookingDto> bookings = new ArrayList<>();
    }

    @Data
    private static class BookingDto {
        private String id;
        private String startTime;
        private int durationInMinutes;
        private String description;
        private String ticket;
        private String notes;
    }
}

package io.github.marmer.sworhm.rest.v1;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/days/{bookingDay}/bookings")
public class BookingController {
    @GetMapping
    public String getBookings(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate bookingDay) {
        return "{\n" +
                "  \"day\": \"2002-02-01\",\n" +
                "  \"entries\": [\n" +
                "    {\n" +
                "      \"id\": \"65b8818f-0320-450b-9da0-49f3269bafd7\",\n" +
                "      \"startTime\": \"00:55\",\n" +
                "      \"durationInMinutes\": 45,\n" +
                "      \"description\": \"another one bites the dust\",\n" +
                "      \"ticket\": \"JIRA-666\",\n" +
                "      \"notes\": \"knocking on heavens door\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"1412c9ec-4abe-44d2-a8af-406c45a55b54\",\n" +
                "      \"startTime\": \"01:55\",\n" +
                "      \"durationInMinutes\": 117,\n" +
                "      \"description\": \"stay alive\",\n" +
                "      \"ticket\": \"JIRA-999\",\n" +
                "      \"notes\": \"cheek to cheek\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    @Value
    public static class BookingsDto {
        private final String bookingDay;
    }
}

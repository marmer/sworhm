package io.github.marmer.sworhm.web.ui.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingDTO {
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate day;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private LocalTime duration;
    private String notes;
    private String ticket;
    private String description;
}

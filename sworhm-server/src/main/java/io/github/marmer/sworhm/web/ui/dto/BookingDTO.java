package io.github.marmer.sworhm.web.ui.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingDTO {
    private String id;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime duration;
    private String notes;
    private String ticket;
    private String description;
}

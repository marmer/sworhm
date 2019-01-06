package io.github.marmer.sworhm.web.ui.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BookingDTO {
    private String id;
    private String description;
    private LocalTime startTime;
}

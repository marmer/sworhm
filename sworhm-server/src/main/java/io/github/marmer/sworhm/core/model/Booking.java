package io.github.marmer.sworhm.core.model;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Wither
@Builder
public class Booking {
    private final String id;
    private final LocalDate day;
    private final LocalTime startTime;
    private final int durationInMinutes;
    private final String notes;
    private final String ticket;
    private final String description;
}

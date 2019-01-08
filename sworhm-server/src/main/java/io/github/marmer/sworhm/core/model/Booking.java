package io.github.marmer.sworhm.core.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class Booking {
    private final String id;
    private final BookingDay day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    /**
     * Duration in Minutes
     */
    private final int duration;
    private final String notes;
    private final String ticket;
    private final String description;
}

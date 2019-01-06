package io.github.marmer.sworhm.core.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class Booking {
    private final String id;
    private final LocalTime startTime;
    private final BookingDay day;
}

package io.github.marmer.sworhm.core.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BookingDay {
    private final String id;
    private final LocalDate day;
}

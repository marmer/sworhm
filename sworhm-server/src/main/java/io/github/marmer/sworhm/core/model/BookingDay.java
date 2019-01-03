package io.github.marmer.sworhm.core.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BookingDay {
    private final LocalDate day;
}

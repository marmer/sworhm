package io.github.marmer.sworhm.core.model;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDate;

@Value
@Wither
@Builder
public class BookingDay {
    private final String id;
    private final LocalDate day;
}

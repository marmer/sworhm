package io.github.marmer.sworhm.core.persistence.entity;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingDayEntityTestdata {
    public static BookingDayEntity.BookingDayEntityBuilder newBookingDayEntity(final ValueProvider valueProvider) {
        return BookingDayEntity.builder()
                .day(valueProvider.nextLocalDate());
    }
}

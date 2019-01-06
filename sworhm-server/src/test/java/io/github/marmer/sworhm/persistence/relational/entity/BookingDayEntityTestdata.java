package io.github.marmer.sworhm.persistence.relational.entity;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingDayEntityTestdata {
    public static BookingDayEntity.BookingDayEntityBuilder newBookingDayEntity(final ValueProvider valueProvider) {
        return BookingDayEntity.builder()
                .day(valueProvider.nextLocalDate());
    }
}

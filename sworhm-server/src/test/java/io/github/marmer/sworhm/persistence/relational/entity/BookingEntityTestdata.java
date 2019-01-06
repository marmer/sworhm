package io.github.marmer.sworhm.persistence.relational.entity;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingEntityTestdata {
    public static BookingEntity.BookingEntityBuilder newBookingEntity(final ValueProvider valueProvider) {
        return BookingEntity.builder()
                .startTime(valueProvider.nextLocalTime())
                .day(BookingDayEntityTestdata.newBookingDayEntity(valueProvider).build());
    }

}

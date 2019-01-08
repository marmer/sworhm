package io.github.marmer.sworhm.persistence.relational.entity;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingEntityTestdata {
    public static BookingEntity.BookingEntityBuilder newBookingEntity(final ValueProvider valueProvider) {
        return BookingEntity.builder()
                .id(null)
                .day(BookingDayEntityTestdata.newBookingDayEntity(valueProvider).build())
                .startTime(valueProvider.nextLocalTime())
                .endTime(valueProvider.nextLocalTime())
                .durationInMinutes(valueProvider.nextInt())
                .notes(valueProvider.nextString())
                .ticket(valueProvider.nextString())
                .description(valueProvider.nextString());
    }

}

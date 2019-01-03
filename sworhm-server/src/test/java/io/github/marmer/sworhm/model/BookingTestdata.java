package io.github.marmer.sworhm.model;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingTestdata {
    public static Booking.BookingBuilder newBooking(final ValueProvider valueProvider) {
        return Booking.builder()
                .startTime(valueProvider.nextLocalTime())
                .day(BookingDayTestdata.newBookingDay(valueProvider).build());
    }
}

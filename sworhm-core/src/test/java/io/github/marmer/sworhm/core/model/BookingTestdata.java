package io.github.marmer.sworhm.core.model;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingTestdata {
    public static Booking.BookingBuilder newBooking(final ValueProvider valueProvider) {
        return Booking.builder()
                .startTime(valueProvider.nextLocalTime());
    }
}

package io.github.marmer.sworhm.model;

import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingDayTestdata {
    public static BookingDay.BookingDayBuilder newBookingDay(final ValueProvider valueProvider) {
        return BookingDay.builder()
                .day(valueProvider.nextLocalDate());
    }
}

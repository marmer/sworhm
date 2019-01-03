package io.github.marmer.sworhm.model;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;

public class Testdatagenerator extends TestdatageneratorBaseJUnit5 {
    public Booking.BookingBuilder newBooking() {
        return BookingTestdata.newBooking(getValueProvider());
    }

    public BookingDay.BookingDayBuilder newBookingDay() {
        return BookingDayTestdata.newBookingDay(getValueProvider());
    }
}

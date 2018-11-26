package io.github.marmer.sworhm.core.model;

import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;

public class Testdatagenerator extends TestdatageneratorBaseJUnit5 {
    public Booking.BookingBuilder newBooking() {
        return BookingTestdata.newBooking(getValueProvider());
    }
}

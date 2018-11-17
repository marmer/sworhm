package io.github.marmer.sworhm.core.model;

import io.github.marmer.sworhm.testutils.testdata.TestdatageneratorBase;

public class Testdatagenerator extends TestdatageneratorBase {
    public Booking.BookingBuilder newBooking() {
        return BookingTestdata.newBooking(getValueProvider());
    }
}

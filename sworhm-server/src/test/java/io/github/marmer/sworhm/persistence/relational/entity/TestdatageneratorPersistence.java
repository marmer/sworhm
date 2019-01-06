package io.github.marmer.sworhm.persistence.relational.entity;

import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;

public class TestdatageneratorPersistence extends TestdatageneratorBaseJUnit5 {
    public BookingEntity.BookingEntityBuilder newBookingEntity() {
        return BookingEntityTestdata.newBookingEntity(getValueProvider());
    }

    public BookingDayEntity.BookingDayEntityBuilder newBookingDayEntity() {
        return BookingDayEntityTestdata.newBookingDayEntity(getValueProvider());
    }
}

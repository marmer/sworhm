package io.github.marmer.sworhm.model;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;
import lombok.Getter;

import static io.github.benas.randombeans.FieldDefinitionBuilder.field;

public class Testdatagenerator extends TestdatageneratorBaseJUnit5 {
    @Getter
    private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(getClass().getName().hashCode())
            .exclude(field().named("id").ofType(String.class).get())
            .exclude(field().named("version").ofType(Long.class).get())
            .build();
    public Booking.BookingBuilder newBooking() {
        return random.nextObject(Booking.BookingBuilder.class);
    }

    public BookingDay.BookingDayBuilder newBookingDay() {
        return random.nextObject(BookingDay.BookingDayBuilder.class);
    }
}

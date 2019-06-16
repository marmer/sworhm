package io.github.marmer.sworhm.model;

import io.github.marmer.sworhm.core.model.Booking;
import lombok.Getter;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class Testdatagenerator {
    @Getter
    private final EasyRandom random = new EasyRandom(new EasyRandomParameters()
            .seed(getClass().getName().hashCode())
            .excludeField(field ->
                    field.getName().equals("version") &&
                            field.getType().equals(Long.class)));

    public Booking.BookingBuilder newBooking() {
        return random.nextObject(Booking.BookingBuilder.class);
    }
}

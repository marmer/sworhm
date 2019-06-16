package io.github.marmer.sworhm.persistence.relational.entity;

import lombok.Getter;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class TestdatageneratorPersistence {
    @Getter
    private final EasyRandom random = new EasyRandom(new EasyRandomParameters()
            .seed(getClass().getName().hashCode())
            .excludeField(field ->
                    field.getName().equals("version") &&
                            field.getType().equals(Long.class)));

    public BookingDbo newBookingDbo() {
        return random.nextObject(BookingDbo.class);
    }

}

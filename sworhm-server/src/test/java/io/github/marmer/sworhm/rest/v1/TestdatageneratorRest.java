package io.github.marmer.sworhm.rest.v1;

import lombok.Getter;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.extension.Extension;

public class TestdatageneratorRest implements Extension {
    @Getter
    private final EasyRandom random = new EasyRandom(new EasyRandomParameters()
            .seed(getClass().getName().hashCode()));

    public BookingController.BookingDto newBookingDto() {
        return random.nextObject(BookingController.BookingDto.class);
    }
}

package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.BookingService;
import io.github.marmer.sworhm.core.model.Booking;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Named
public class SimpleBookingService implements BookingService {

    @Override
    public List<Booking> findBookingsByDay(final LocalDate day) {
        // TODO: marmer 16.06.2019 implement me!
        return Collections.emptyList();
    }
}

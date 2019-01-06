package io.github.marmer.sworhm.web.ui.dto;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;

public class BookingDTOTestdata {
    public static BookingDTO newBookingDTO(final ValueProvider valueProvider) {
        return new BookingDTO()
                .setId(valueProvider.nextString())
                .setDescription(valueProvider.nextString())
                .setStartTime(valueProvider.nextLocalTime());
    }
}

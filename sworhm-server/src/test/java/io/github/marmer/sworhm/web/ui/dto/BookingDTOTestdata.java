package io.github.marmer.sworhm.web.ui.dto;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;
import io.github.marmer.sworhm.web.ui.BookingController;

public class BookingDTOTestdata {
    public static BookingController.BookingDTO newBookingDTO(final ValueProvider valueProvider) {
        return new BookingController.BookingDTO()
                .setId(valueProvider.nextString())
                .setDescription(valueProvider.nextString())
                .setStartTime(valueProvider.nextLocalTime());
    }
}

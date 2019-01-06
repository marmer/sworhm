package io.github.marmer.sworhm.web.ui.dto;

import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;
import io.github.marmer.sworhm.web.ui.BookingController;

public class TestdatageneratorWebUiDTO extends TestdatageneratorBaseJUnit5 {
    public BookingController.BookingDTO newBookingDTO() {
        return BookingDTOTestdata.newBookingDTO(getValueProvider());
    }
}

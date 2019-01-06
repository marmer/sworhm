package io.github.marmer.sworhm.web.ui.dto;

import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;

public class TestdatageneratorWebUiDTO extends TestdatageneratorBaseJUnit5 {
    public BookingDTO newBookingDTO() {
        return BookingDTOTestdata.newBookingDTO(getValueProvider());
    }
}

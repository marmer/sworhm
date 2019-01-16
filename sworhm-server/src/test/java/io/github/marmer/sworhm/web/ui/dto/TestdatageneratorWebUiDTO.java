package io.github.marmer.sworhm.web.ui.dto;

import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;

public class TestdatageneratorWebUiDTO extends TestdatageneratorBaseJUnit5 {
    public BookingDTO newBookingDTO() {
        // TODO: marmer 16.01.2019 Try to replace the testdata classes with a preconfigured io.github.benas.randombeans.EnhancedRandomBuilder.
        //  The "seed" should be the most important part and don't forget to ignore "id" and "version" fields somehow

        return BookingDTOTestdata.newBookingDTO(getValueProvider());
    }
}

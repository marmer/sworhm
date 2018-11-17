package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.testutils.testdata.IncrementalValueProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SimpleBookingServiceTest {
    @RegisterExtension
    private final IncrementalValueProvider incrementalValueProvider = new IncrementalValueProvider();

    @InjectMocks
    private SimpleBookingService underTest;

    @Test
    public void testStoreBooking_BookingGiven_ShouldServePersistedVersion()
            throws Exception {
        // Preparation

        // Execution

        // Assertion
        // TODO: marmer 17.11.2018 go on here
    }

}
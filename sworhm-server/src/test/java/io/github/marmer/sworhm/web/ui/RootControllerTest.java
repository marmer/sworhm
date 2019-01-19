package io.github.marmer.sworhm.web.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ExtendWith(MockitoExtension.class)
class RootControllerTest {
    @InjectMocks
    private RootController underTest;

    @Test
    @DisplayName("Rootcall should redirect to bookings default page")
    void testGetRoot_RootcallShouldRedirectToBookingsDefaultPage()
            throws Exception {
        // Execution
        final String result = underTest.getDefaultSite();

        // Assertion
        assertThat(result, is("redirect:/days/:today/bookings"));
    }
}
package io.github.marmer.sworhm.web.ui;

import io.github.marmer.sworhm.core.SystemTimeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class BookingControllerTest {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private SystemTimeService systemTimeService;

    @Test
    @DisplayName("Get request for any bookings should redirect to todays bookings date")
    void testGetDefaultBookingPage_GetRequestForAnyBookingsShouldRedirectToTodaysBookings()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2002, 2, 20, 1, 2);
        when(systemTimeService.now()).thenReturn(now);

        // Execution
        final ResultActions result = mockMvc.perform(get("/bookings")
                .accept(APPLICATION_JSON));

        // Assertion
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/day/:2002-02-20/bookings"));
    }

    @Test
    @DisplayName("Get request for todays bookings should redirect to todays bookings date")
    void testGetTodaysBookingPage_GetRequestForAnyBookingsShouldRedirectToTodaysBookings()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2002, 2, 20, 1, 2);
        when(systemTimeService.now()).thenReturn(now);

        // Execution
        final ResultActions result = mockMvc.perform(get("/day/:today/bookings")
                .accept(APPLICATION_JSON));

        // Assertion
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/day/:2002-02-20/bookings"));
    }

}
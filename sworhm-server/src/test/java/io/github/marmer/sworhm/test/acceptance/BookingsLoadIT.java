package io.github.marmer.sworhm.test.acceptance;

import io.github.marmer.sworhm.core.SystemTimeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookingsLoadIT {
    @Inject
    private MockMvc mockMvc;

    @MockBean
    private SystemTimeService systemTimeService;

    @Test
    @DisplayName("Request for any bookings should serve Bookings for today")
    void testGetBookings_RequestForAnyBookingsShouldServeBookingsForToday()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2000, 4, 3, 2, 1);
        when(systemTimeService.now()).thenReturn(now);

        // Execution
        final ResultActions result = mockMvc.perform(get("/bookings")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // Assertion
        result.andExpect(redirectedUrl("/day/:2000-04-03/bookings"));
    }
}

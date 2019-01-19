package io.github.marmer.sworhm.test.acceptance.web.ui;

import io.github.marmer.sworhm.web.ui.RootController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RootControllerIT {
    @Inject
    private RootController underTest;
    @Inject
    private MockMvc mockMvc;

    @Test
    @DisplayName("Rootcall should redirect to bookings default page")
    void testGetRoot_RootcallShouldRedirectToBookingsDefaultPage()
            throws Exception {
        // Preparation

        // Execution
        final ResultActions result = mockMvc.perform(get("/"));

        // Assertion
        result.andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/days/:today/bookings"));
    }
}
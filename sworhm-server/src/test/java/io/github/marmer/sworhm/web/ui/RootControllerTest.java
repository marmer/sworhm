package io.github.marmer.sworhm.web.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class RootControllerTest {
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
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/bookings"));
    }
}
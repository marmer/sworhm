package io.github.marmer.sworhm.test.acceptance;

import io.github.marmer.sworhm.web.ui.RootController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RootControllerTest {
    @Autowired
    private RootController underTest;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Rootcall should redirect to bookings default page")
    public void testGetRoot_RootcallShouldRedirectToBookingsDefaultPage()
            throws Exception {
        // Preparation

        // Execution
        final ResultActions result = mockMvc.perform(get("/"));

        // Assertion
        result.andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("/bookings"));
    }
}
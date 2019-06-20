package io.github.marmer.sworhm.test.acceptance.rest;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.testutils.springhelper.DbCleanupService;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingsDeleteIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @Inject
    private TransactionlessTestEntityManager entityManager;
    @Inject
    private MockMvc mockMvc;

    @Inject
    private DbCleanupService dbCleanupService;

    @BeforeEach
    void setUp() {
        dbCleanupService.clearAll();
    }


    @Test
    @DisplayName("Existing bookings should be removed and no content should be served")
    void deleteBooking_ExistingBookingsShouldBeRemovedAndNoContentShouldBeServed()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(1990, 7, 20);
        final String id = "1412c9ec-4abe-44d2-a8af-406c45a55b54";

        entityManager.persist(testdatageneratorPersistence.newBookingDbo()
                .setId(id));

        // Execution
        final var result = mockMvc.perform(delete("/api/v1/days/{bookingDay}/bookings/{id}", day, id));

        // Assertion
        assertAll(
                () -> result.andExpect(status().isNoContent()),
                () -> assertThat(entityManager.findAllOf(BookingDbo.class),
                        is(empty())));
    }

    @Test
    @DisplayName("At not existing bookings no content should be served")
    void deleteBooking_AtNotExistingBookingsNoContentShouldBeServed()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(1990, 7, 20);
        final String id = "1412c9ec-4abe-44d2-a8af-406c45a55b54";

        // Execution
        final var result = mockMvc.perform(delete("/api/v1/days/{bookingDay}/bookings/{id}", day, id));

        // Assertion
        assertAll(
                () -> result.andExpect(status().isNoContent()),
                () -> assertThat(entityManager.findAllOf(BookingDbo.class),
                        is(empty())));
    }
}

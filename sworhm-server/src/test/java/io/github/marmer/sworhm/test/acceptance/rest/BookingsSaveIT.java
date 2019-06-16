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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;

import static io.github.marmer.sworhm.persistence.relational.entity.BookingDboMatcher.isBookingDbo;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingsSaveIT {
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
    @DisplayName("Not existing bookings should be created")
    void putBooking_NotExistingBookingsShouldBeCreated()
            throws Exception {
        // Preparation
        final String bookingJson = "{\n" +
                "  \"id\": \"1412c9ec-4abe-44d2-a8af-406c45a55b54\",\n" +
                "  \"startTime\": \"01:55\",\n" +
                "  \"durationInMinutes\": 117,\n" +
                "  \"description\": \"stay alive\",\n" +
                "  \"ticket\": \"JIRA-999\",\n" +
                "  \"notes\": \"cheek to cheek\"\n" +
                "}\n";
        final LocalDate day = LocalDate.of(1990, 7, 20);
        final String id = "1412c9ec-4abe-44d2-a8af-406c45a55b54";

        // Execution
        final var result = mockMvc.perform(put("/api/v1/days/{bookingDay}/bookings/{id}", day, id)
                .content(bookingJson)
                .contentType(MediaType.APPLICATION_JSON));

        // Assertion
        assertAll(
                () -> result.andExpect(status().isOk()),
                () -> assertThat(entityManager.findAllOf(BookingDbo.class),
                        contains(isBookingDbo()
                                .withId(id)
                                .withStartTime(LocalTime.of(1, 55))
                                .withDurationInMinutes(117)
                                .withDescription("stay alive")
                                .withTicket("JIRA-999")
                                .withNotes("cheek to cheek")
                        )));
    }


    @Test
    @DisplayName("Existing booknig should be overridden")
    void putBooking_ExistingBooknigShouldBeOverridden()
            throws Exception {
        // Preparation
        final String bookingJson = "{\n" +
                "  \"id\": \"1412c9ec-4abe-44d2-a8af-406c45a55b54\",\n" +
                "  \"startTime\": \"01:55\",\n" +
                "  \"durationInMinutes\": 117,\n" +
                "  \"description\": \"stay alive\",\n" +
                "  \"ticket\": \"JIRA-999\",\n" +
                "  \"notes\": \"cheek to cheek\"\n" +
                "}\n";
        final LocalDate day = LocalDate.of(1990, 7, 20);
        final String id = "1412c9ec-4abe-44d2-a8af-406c45a55b54";

        entityManager.persist(testdatageneratorPersistence.newBookingDbo()
                .setId(id));

        // Execution
        final var result = mockMvc.perform(put("/api/v1/days/{bookingDay}/bookings/{id}", day, id)
                .content(bookingJson)
                .contentType(MediaType.APPLICATION_JSON));

        // Assertion
        assertAll(
                () -> result.andExpect(status().isOk()),
                () -> assertThat(entityManager.findAllOf(BookingDbo.class),
                        contains(isBookingDbo()
                                .withId(id)
                                .withStartTime(LocalTime.of(1, 55))
                                .withDurationInMinutes(117)
                                .withDescription("stay alive")
                                .withTicket("JIRA-999")
                                .withNotes("cheek to cheek")
                        )));
    }
}

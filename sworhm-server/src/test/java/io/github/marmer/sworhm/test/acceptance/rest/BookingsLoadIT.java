package io.github.marmer.sworhm.test.acceptance.rest;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingsLoadIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @Inject
    private TransactionlessTestEntityManager entityManager;
    @Inject
    private MockMvc mockMvc;

    @Test
    @DisplayName("Existing bookings shuold be served")
    void getBookings_ExistingBookingsShuoldBeServed()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2002, 3, 4);
        final BookingDbo booking1 = entityManager.persist(testdatageneratorPersistence.newBookingDbo()
                .setDay(day)
                .setId("65b8818f-0320-450b-9da0-49f3269bafd7")
                .setStartTime(LocalTime.of(0, 55))
                .setDurationInMinutes(45)
                .setDescription("another one bites the dust")
                .setTicket("JIRA-666")
                .setNotes("knocking on heavens door")
        );
        final BookingDbo booking2 = entityManager.persist(testdatageneratorPersistence.newBookingDbo()
                .setDay(day)
                .setId("1412c9ec-4abe-44d2-a8af-406c45a55b54")
                .setStartTime(LocalTime.of(1, 55))
                .setDurationInMinutes(117)
                .setDescription("stay alive")
                .setTicket("JIRA-999")
                .setNotes("cheek to cheek")
        );

        // Execution
        final var result = mockMvc.perform(get("/api/v1/days/2002-03-04/bookings"));

        // Assertion
        result.andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"day\": \"2002-03-04\",\n" +
                        "  \"bookings\": [\n" +
                        "    {\n" +
                        "      \"id\": \"65b8818f-0320-450b-9da0-49f3269bafd7\",\n" +
                        "      \"startTime\": \"00:55\",\n" +
                        "      \"durationInMinutes\": 45,\n" +
                        "      \"description\": \"another one bites the dust\",\n" +
                        "      \"ticket\": \"JIRA-666\",\n" +
                        "      \"notes\": \"knocking on heavens door\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"id\": \"1412c9ec-4abe-44d2-a8af-406c45a55b54\",\n" +
                        "      \"startTime\": \"01:55\",\n" +
                        "      \"durationInMinutes\": 117,\n" +
                        "      \"description\": \"stay alive\",\n" +
                        "      \"ticket\": \"JIRA-999\",\n" +
                        "      \"notes\": \"cheek to cheek\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}\n"));
    }
}

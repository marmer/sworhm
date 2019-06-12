package io.github.marmer.sworhm.test.acceptance.web.ui;

import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDayDbo;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.testutils.springhelper.DbCleanupService;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static io.github.marmer.sworhm.persistence.relational.entity.BookingDayDboMatcher.isBookingDayDbo;
import static io.github.marmer.sworhm.persistence.relational.entity.BookingDboMatcher.isBookingDbo;
import static io.github.marmer.sworhm.web.ui.dto.BookingDTOMatcher.isBookingDTO;
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingsStoreIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();

    @Inject
    private MockMvc mockMvc;
    @SpyBean
    private SystemTimeService systemTimeService;
    @Inject
    private TransactionlessTestEntityManager em;
    @Inject
    private DbCleanupService dbCleanupService;

    @BeforeEach
    void setUp() throws Exception {
        dbCleanupService.clearAll();
    }

    @Test
    @DisplayName("Should store new booking")
    void testPostBooking_ShouldStoreNewBooking()
            throws Exception {
        // Execution
        final ResultActions result = mockMvc.perform(post("/days/:{pathDay}/bookings", "2018-05-03")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "funky description"));

        // Assertions
        result.andExpect(status().isOk())
                .andExpect(view().name("bookings"))
                .andExpect(model().attribute("bookings",
                        contains(isBookingDTO()
                                .withDescription("funky description")
                                .withDay(LocalDate.of(2018, 5, 3)))));

        final List<BookingDbo> dbBookings = em.findAllOf(BookingDbo.class);
        Assert.assertThat(dbBookings, contains(isBookingDbo()
                .withDay(isBookingDayDbo()
                        .withDay(LocalDate.of(2018, 5, 3)))
                .withDescription("funky description")));
    }

    @Test
    @DisplayName("Previously created days are reused")
    void testPostBooking_PreviouslyCreatedDaysAreReused()
            throws Exception {
        // Preparation
        final BookingDayDbo oldDay = em.persistAndFlush(testdatageneratorPersistence.newBookingDayEntity()
                .day(LocalDate.of(2018, 5, 3))
                .build());

        // Execution
        final ResultActions result = mockMvc.perform(post("/days/:{pathDay}/bookings", "2018-05-03")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "funky description"));

        // Assertions
        result.andExpect(status().isOk())
                .andExpect(view().name("bookings"))
                .andExpect(model().attribute("bookings",
                        contains(isBookingDTO()
                                .withDescription("funky description")
                                .withDay(LocalDate.of(2018, 5, 3)))));

        final List<BookingDbo> dbBookings = em.findAllOf(BookingDbo.class);
        Assert.assertThat(dbBookings, contains(isBookingDbo()
                .withDay(oldDay)
                .withDescription("funky description")));
    }

}

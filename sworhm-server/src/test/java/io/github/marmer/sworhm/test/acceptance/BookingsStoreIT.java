package io.github.marmer.sworhm.test.acceptance;

import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
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

import static io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntityMatcher.isBookingDayEntity;
import static io.github.marmer.sworhm.persistence.relational.entity.BookingEntityMatcher.isBookingEntity;
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
    private TransactionlessTestEntityManager transactionlessTestEntityManager;
    @Inject
    private DbCleanupService dbCleanupService;

    @BeforeEach
    void setUp() throws Exception {
        dbCleanupService.clearAll();
    }

    @Test
    @DisplayName("Booking entity post should be stored with day of Pat")
    void testTestMethodName_BookingEntityPostShouldBeStoredWithDayOfPath()
            throws Exception {
        // Execution
        final ResultActions result = mockMvc.perform(post("/day/:{pathDay}/bookings", "2018-05-03")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "funky description"));

        // Assertions
        result.andExpect(status().isOk())
                .andExpect(view().name("bookings"))
                .andExpect(model().attribute("bookings",
                        contains(isBookingDTO()
                                .withDescription("funky description")
                                .withDay(LocalDate.of(2018, 5, 3)))));

        final List<BookingEntity> dbBookings = transactionlessTestEntityManager.findAllOf(BookingEntity.class);
        Assert.assertThat(dbBookings, contains(isBookingEntity()
                .withDay(isBookingDayEntity()
                        .withDay(LocalDate.of(2018, 5, 3)))
                .withDescription("funky description")));
    }

}

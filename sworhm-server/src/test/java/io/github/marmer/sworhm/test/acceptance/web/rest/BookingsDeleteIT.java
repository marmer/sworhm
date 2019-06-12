package io.github.marmer.sworhm.test.acceptance.web.rest;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDayDbo;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.testutils.springhelper.DbCleanupService;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingsDeleteIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @Inject
    private MockMvc mockMvc;
    @Inject
    private DbCleanupService dbCleanupService;
    @Inject
    private TransactionlessTestEntityManager em;

    @Test
    @DisplayName("Sould delete selected entities only")
    void testDeleteBooking_SouldDeleteSelectedEntities()
            throws Exception {
        // Preparation
        final BookingDayDbo day = testdatageneratorPersistence.newBookingDayEntity().day(LocalDate.of(2018, 3, 4)).build();
        final BookingDbo toKeep = testdatageneratorPersistence.newBookingEntity().day(day).build();
        final BookingDbo toRemove = testdatageneratorPersistence.newBookingEntity().day(day).build();

        em.doTransactional(() -> {
            em.persistAndFlush(toKeep);
            em.persistAndFlush(toRemove);
        });

        // Execution
        mockMvc.perform(delete("/api/v1/days/:2018-03-04/bookings/{bookingId}", toRemove.getId()))
                .andExpect(status().isNoContent());

        // Assertion
        em.doTransactional(() -> {
            final List<BookingDbo> bookings = em.findAllOf(BookingDbo.class);
            Assert.assertThat(bookings, contains(toKeep));
        });
    }

}

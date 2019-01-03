package io.github.marmer.sworhm.test.acceptance;

import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.core.persistence.entity.BookingEntity;
import io.github.marmer.sworhm.core.persistence.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.testutils.springhelper.DbCleanupService;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookingsLoadIT {
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();

    @Inject
    private MockMvc mockMvc;
    @MockBean
    private SystemTimeService systemTimeService;
    @Inject
    private TransactionlessTestEntityManager transactionlessTestEntityManager;
    @Inject
    private DbCleanupService dbCleanupService;

    @Test
    @DisplayName("Request for any bookings should serve Bookings for today")
    void testGetBookings_RequestForAnyBookingsShouldServeBookingsForToday()
            throws Exception {
        // Preparation
        final LocalDateTime now = LocalDateTime.of(2000, 4, 3, 2, 1);
        when(systemTimeService.now()).thenReturn(now);

        // Execution
        final ResultActions result = mockMvc.perform(get("/bookings"));

        // Assertion
        result.andExpect(redirectedUrl("/day/:today/bookings"));
    }

    @Test
    @DisplayName("Request for todays booking should serve Bookings of Today only")
    void testGetTodaysBookingsPage_RequestForTodaysBookingShouldServeBookingsOfTodayOnly()
            throws Exception {
        // Preparation
        final LocalDate today = LocalDate.of(2002, 1, 2);
        final BookingEntity yesterdaysBookingEntity = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(today.minusDays(1)).build()).build();
        final BookingEntity todaysBookingEntity = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(today).build()).build();
        final BookingEntity tomorrowsBookingEntity = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(today.plusDays(1)).build()).build();

        dbCleanupService.clearAll();
        transactionlessTestEntityManager.persistAndGetId(todaysBookingEntity);
        transactionlessTestEntityManager.persistAndGetId(tomorrowsBookingEntity);
        transactionlessTestEntityManager.persistAndGetId(yesterdaysBookingEntity);

        // Execution
        final ResultActions result = mockMvc.perform(get("/bookings"));

        // Assertion
        result.andExpect(model().attribute("bookings", todaysBookingEntity.getStartTime()))
                .andExpect(view().name("bookings"));
    }
}

package io.github.marmer.sworhm.test.acceptance.web.ui;

import io.github.marmer.sworhm.core.SystemTimeService;
import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.testutils.springhelper.DbCleanupService;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.github.marmer.sworhm.web.ui.dto.BookingDTOMatcher.isBookingDTO;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingsLoadIT {
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
        result.andExpect(redirectedUrl("/days/:today/bookings"));
    }

    @Test
    @DisplayName("Request for todays booking should serve Bookings of Today only")
    void testGetTodaysBookingsPage_RequestForTodaysBookingShouldServeBookingsOfTodayOnly()
            throws Exception {
        // Preparation
        final LocalDate today = LocalDate.of(2002, 1, 2);
        when(systemTimeService.now()).thenReturn(LocalDateTime.of(2002, 1, 2, 15, 30));
        final BookingDbo yesterdaysBookingDbo = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(today.minusDays(1)).build()).build();
        final BookingDbo todaysBookingDbo = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(today).build()).build();
        final BookingDbo tomorrowsBookingDbo = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(today.plusDays(1)).build()).build();

        dbCleanupService.clearAll();
        transactionlessTestEntityManager.persistAndGetId(todaysBookingDbo);
        transactionlessTestEntityManager.persistAndGetId(tomorrowsBookingDbo);
        transactionlessTestEntityManager.persistAndGetId(yesterdaysBookingDbo);

        // Execution
        final ResultActions result = mockMvc.perform(get("/days/:today/bookings"));

        // Assertion
        result.andExpect(model().attribute("bookings",
                contains(isBookingDTO()
                        .withStartTime(todaysBookingDbo.getStartTime())
                        .withId(todaysBookingDbo.getId()))))
                .andExpect(view().name("bookings"));
    }

    @Test
    @DisplayName("Request for any days booking should serve Bookings of Today only")
    void testGetTodaysBookingsPage_RequestForAnyDaysBookingShouldServeBookingsOfTodayOnly()
            throws Exception {
        // Preparation
        final LocalDate anyDay = LocalDate.of(2002, 1, 2);
        final BookingDbo yesterdaysBookingDbo = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(anyDay.minusDays(1)).build()).build();
        final BookingDbo todaysBookingDbo = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(anyDay).build()).build();
        final BookingDbo tomorrowsBookingDbo = testdatageneratorPersistence.newBookingEntity()
                .day(testdatageneratorPersistence.newBookingDayEntity()
                        .day(anyDay.plusDays(1)).build()).build();

        dbCleanupService.clearAll();
        transactionlessTestEntityManager.persistAndGetId(todaysBookingDbo);
        transactionlessTestEntityManager.persistAndGetId(tomorrowsBookingDbo);
        transactionlessTestEntityManager.persistAndGetId(yesterdaysBookingDbo);

        // Execution
        final ResultActions result = mockMvc.perform(get("/days/:2002-01-02/bookings"));

        // Assertion
        result.andExpect(model().attribute("bookings",
                contains(isBookingDTO()
                        .withStartTime(todaysBookingDbo.getStartTime())
                        .withId(todaysBookingDbo.getId()))))
                .andExpect(view().name("bookings"));
    }
}

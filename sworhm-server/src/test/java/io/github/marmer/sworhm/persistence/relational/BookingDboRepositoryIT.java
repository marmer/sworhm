package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDboRepository;
import io.github.marmer.sworhm.testutils.springhelper.DbCleanupService;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

@DataJpaTest
@Import({TransactionlessTestEntityManager.class, DbCleanupService.class})
class BookingDboRepositoryIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @Inject
    private DbCleanupService dbCleanupService;
    @Inject
    private BookingDboRepository underTest;
    @Inject
    private TransactionlessTestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        dbCleanupService.clearAll();
    }

    @Test
    @DisplayName("Only existing Bookings should be loaded")
    void findAllByDay_ExistingBookingsShouldBeLoaded()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(1985, 1, 2);
        final BookingDbo rightDbo = entityManager.persistAndFlush(testdatageneratorPersistence.newBookingDbo().setDay(day));
        final BookingDbo wrongDboBefore = entityManager.persistAndFlush(testdatageneratorPersistence.newBookingDbo().setDay(day.plusDays(1)));
        final BookingDbo wrongDboAfter = entityManager.persistAndFlush(testdatageneratorPersistence.newBookingDbo().setDay(day.minusDays(1)));

        // Execution
        final var result = underTest.findAllByDay(day);

        // Assertion
        assertThat(result, contains(rightDbo));
    }

    @Test
    @DisplayName("No existing booking shuold be stored")
    void saveOrUpdate_NoExistingBookingShuoldBeStored()
            throws Exception {
        // Preparation
        final BookingDbo bookingDbo = testdatageneratorPersistence.newBookingDbo();

        // Execution
        underTest.saveOrUpdate(bookingDbo);

        // Assertion
        assertThat(entityManager.findAllOf(BookingDbo.class), contains(bookingDbo));
    }

    @Test
    @DisplayName("Existing bookings should get updated")
    void saveOrUpdate_ExistingBookingsShouldGetUpdated()
            throws Exception {
        // Preparation
        final BookingDbo oldBooking = entityManager.persistAndFlush(testdatageneratorPersistence.newBookingDbo());
        final BookingDbo updatedBooking = testdatageneratorPersistence.newBookingDbo().setId(oldBooking.getId());

        // Execution
        underTest.saveOrUpdate(updatedBooking);

        // Assertion
        assertThat(entityManager.findAllOf(BookingDbo.class), contains(updatedBooking));
    }

    @Test
    @DisplayName("Multiple saves with bookings with different ids should be stored seperately")
    void saveOrUpdate_MultipleSavesWithBookingsWithDifferentIdsShouldBeStoredSeperately()
            throws Exception {
        // Preparation
        final BookingDbo oldBooking = entityManager.persistAndFlush(testdatageneratorPersistence.newBookingDbo());
        final BookingDbo newBooking = testdatageneratorPersistence.newBookingDbo();

        // Execution
        underTest.saveOrUpdate(newBooking);

        // Assertion
        assertThat(entityManager.findAllOf(BookingDbo.class), containsInAnyOrder(oldBooking, newBooking));
    }
}
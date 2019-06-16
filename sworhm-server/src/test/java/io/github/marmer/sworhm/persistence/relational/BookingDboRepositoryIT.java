package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingDboRepository;
import io.github.marmer.sworhm.testutils.springhelper.TransactionlessTestEntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;
import java.time.LocalDate;

import static co.unruly.matchers.StreamMatchers.contains;
import static org.junit.Assert.assertThat;

@DataJpaTest
@Import(TransactionlessTestEntityManager.class)
class BookingDboRepositoryIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @Inject
    private BookingDboRepository underTest;
    @Inject
    private TransactionlessTestEntityManager entityManager;

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
}
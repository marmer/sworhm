package io.github.marmer.sworhm.persistence.relational;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.persistence.entity.BookingEntity;
import io.github.marmer.sworhm.core.persistence.entity.TestdatageneratorPersistence;
import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.persistence.relational.converter.BookingConverter;
import io.github.marmer.sworhm.persistence.relational.repositories.BookingEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaBookingPersistencePortImplTest {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @Mock
    private BookingEntityRepository bookingEntityRepository;
    @Mock
    private BookingConverter bookingConverter;
    @InjectMocks
    private JpaBookingPersistencePortImpl underTest;

    @Test
    @DisplayName("Should serve existing bookings")
    void testFindBookingsByDay_ShouldServeExistingBookings()
            throws Exception {
        // Preparation
        final LocalDate day = testdatageneratorPersistence.getValueProvider().nextLocalDate();
        final BookingEntity bookingEntity = testdatageneratorPersistence.newBookingEntity().build();
        final Booking booking = testdatagenerator.newBooking().build();

        when(bookingEntityRepository.findByDayDay(day)).thenReturn(singletonList(bookingEntity));
        when(bookingConverter.convert(singletonList(bookingEntity))).thenReturn(singletonList(booking));

        // Execution
        final var result = underTest.findBookingsByDay(day);

        // Assertion
        assertThat(result, contains(booking));
    }
}
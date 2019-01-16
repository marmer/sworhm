package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.persistence.relational.converter.internal.BookingConverterFromEntity;
import io.github.marmer.sworhm.persistence.relational.entity.TestdatageneratorPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static io.github.marmer.sworhm.core.model.BookingDayMatcher.isBookingDay;
import static io.github.marmer.sworhm.core.model.BookingMatcher.isBooking;
import static org.junit.Assert.assertThat;

@SpringBootTest
class BookingConverterFromEntityFromDTOTest {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();
    @Inject
    private BookingConverterFromEntity bookingConverterFromEntity;
    @Inject
    private BookingConverterFromEntity underTest;

    @Test
    @DisplayName("Simple entity should be converted into model")
    void testConvert_SimpleEntityShouldBeConvertedIntoModel()
            throws Exception {
        // Preparation
        final var entity = testdatageneratorPersistence.newBookingEntity().build();

        // Execution
        final var result = underTest.convert(entity);

        // Assertion
        assertThat(result, isBooking()
                .withId(entity.getId())
                .withDay(isBookingDay()
                        .withId(entity.getDay().getId())
                        .withDay(entity.getDay().getDay()))
                .withStartTime(entity.getStartTime())
                .withEndTime(entity.getEndTime())
                .withDuration(entity.getDuration())
                .withNotes(entity.getNotes())
                .withTicket(entity.getTicket())
                .withDescription(entity.getDescription())
        );
    }
}
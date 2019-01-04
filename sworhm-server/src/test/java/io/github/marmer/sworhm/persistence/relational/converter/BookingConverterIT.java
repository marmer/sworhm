package io.github.marmer.sworhm.persistence.relational.converter;

import io.github.marmer.sworhm.core.persistence.entity.BookingEntity;
import io.github.marmer.sworhm.core.persistence.entity.TestdatageneratorPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.github.marmer.sworhm.core.model.BookingDayMatcher.isBookingDay;
import static io.github.marmer.sworhm.core.model.BookingMatcher.isBooking;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookingConverterIT {
    @RegisterExtension
    private final TestdatageneratorPersistence testdatageneratorPersistence = new TestdatageneratorPersistence();

    @Autowired
    private BookingConverter underTest;

    @Test
    @DisplayName("Sample conversion")
    void testConvert_DefaultConversion()
            throws Exception {
        // Preparation
        final BookingEntity entity = testdatageneratorPersistence.newBookingEntity().build();

        // Execution
        final var result = underTest.convert(entity);

        // Assertion
        assertThat(result, isBooking()
                .withStartTime(entity.getStartTime())
                .withDay(isBookingDay()
                        .withDay(entity.getDay().getDay())));
    }
}
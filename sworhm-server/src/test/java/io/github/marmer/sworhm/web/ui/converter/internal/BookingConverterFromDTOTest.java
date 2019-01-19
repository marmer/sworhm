package io.github.marmer.sworhm.web.ui.converter.internal;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.core.model.BookingDay;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static io.github.marmer.sworhm.core.model.BookingDayMatcher.isBookingDay;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class BookingConverterFromDTOTest {
    @InjectMocks
    private final BookingConverterFromDTO underTest = new BookingConverterFromDTO() {
        @Override
        public Booking convert(final BookingDTO bookingDTOToStore, final LocalDate day) {
            //dummy
            return null;
        }
    };

    @Test
    @DisplayName("No time should be set to zero minutes")
    void testLocalDateToDurationInMinutes_NoTimeShouldBeSetToZeroMinutes()
            throws Exception {
        // Preparation

        // Execution
        final var result = underTest.localDateToDurationInMinutes(null);

        // Assertion
        assertThat(result, is(0));
    }

    @Test
    @DisplayName("Given time should be computed into minutes")
    void testLocalDateToDurationInMinutes_GivenTimeShouldBeComputedIntoMinutes()
            throws Exception {
        // Preparation

        // Execution
        final var result = underTest.localDateToDurationInMinutes(LocalTime.of(2, 22));

        // Assertion
        assertThat(result, is(142));
    }

    @Test
    @DisplayName("should set given day into given builder")
    void testSetDayOfContent_ShouldSetGivenDayIntoGivenBuilder()
            throws Exception {
        // Preparation
        final LocalDate day = LocalDate.of(2, 3, 4);
        final BookingDay.BookingDayBuilder builder = BookingDay.builder();

        // Execution
        underTest.setDayOfContent(builder, day);

        // Assertion
        assertThat(builder.build(), isBookingDay().withDay(day));
    }
}
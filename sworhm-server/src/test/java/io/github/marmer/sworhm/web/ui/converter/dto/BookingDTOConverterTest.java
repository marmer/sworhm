package io.github.marmer.sworhm.web.ui.converter.dto;

import io.github.marmer.sworhm.core.model.Booking;
import io.github.marmer.sworhm.web.ui.dto.BookingDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class BookingDTOConverterTest {
    @InjectMocks
    private final BookingDTOConverter underTest = new BookingDTOConverter() {
        @Override
        public BookingDTO convert(final Booking source) {
            //dummy
            return null;
        }
    };

    @Test
    @DisplayName("To big amount of minutes should be cut to a max amount for LocalTime")
    void testMinutesToTime_ToBigAmountOfMinutesShouldBeCutToAMaxAmountForLocalTime()
            throws Exception {
        // Preparation

        // Execution
        final var result = underTest.minutesToTime(1440);

        // Assertion
        assertThat(result, is(LocalTime.of(23, 59)));
    }

    @Test
    @DisplayName("Negative amount of minutes should made positive")
    void testMinutesToTime_NegativeAmountOfMinutesShouldMadePositive()
            throws Exception {
        // Preparation

        // Execution
        final var result = underTest.minutesToTime(-154);

        // Assertion
        assertThat(result, is(LocalTime.of(2, 34)));
    }

    @Test
    @DisplayName("Numbers should be translated into LocalTime")
    void testMinutesToTime_NumbersShouldBeTranslatedIntoLocalTime()
            throws Exception {
        // Preparation

        // Execution
        final var result = underTest.minutesToTime(154);

        // Assertion
        assertThat(result, is(LocalTime.of(2, 34)));
    }
}
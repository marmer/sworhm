package io.github.marmer.sworhm.web.ui.converter;

import io.github.marmer.sworhm.model.Testdatagenerator;
import io.github.marmer.sworhm.web.ui.converter.dto.BookingDTOConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.time.LocalTime;

import static io.github.marmer.sworhm.web.ui.dto.BookingDTOMatcher.isBookingDTO;
import static org.junit.Assert.assertThat;

@SpringBootTest
class BookingDTOConverterIT {
    @RegisterExtension
    private final Testdatagenerator testdatagenerator = new Testdatagenerator();
    @Inject
    private BookingDTOConverter underTest;

    @Test
    @DisplayName("Simple model should be converted into dto")
    void testConvert_SimpleModelShouldBeConvertedIntoDTO()
            throws Exception {
        // Preparation
        final var model = testdatagenerator.newBooking()
                .duration(135).build();

        // Execution
        final var result = underTest.convert(model);

        // Assertion
        assertThat(result, isBookingDTO()
                .withId(model.getId())
                .withDay(model.getDay().getDay())
                .withStartTime(model.getStartTime())
                .withEndTime(model.getEndTime())
                .withDuration(LocalTime.of(2, 15))
                .withNotes(model.getNotes())
                .withTicket(model.getTicket())
                .withDescription(model.getDescription())
        );
    }
}
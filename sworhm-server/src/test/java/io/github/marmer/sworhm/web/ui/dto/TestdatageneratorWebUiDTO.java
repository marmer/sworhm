package io.github.marmer.sworhm.web.ui.dto;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.marmer.sworhm.testutils.testdata.junit5.TestdatageneratorBaseJUnit5;
import lombok.Getter;

import static io.github.benas.randombeans.FieldDefinitionBuilder.field;

public class TestdatageneratorWebUiDTO extends TestdatageneratorBaseJUnit5 {
    @Getter
    private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(getClass().getName().hashCode())
            .exclude(field().named("id").ofType(String.class).get())
            .exclude(field().named("version").ofType(Long.class).get())
            .build();

    public BookingDTO newBookingDTO() {
        return random.nextObject(BookingDTO.class);
    }
}

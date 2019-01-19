package io.github.marmer.sworhm.persistence.relational.entity;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.Getter;

import static io.github.benas.randombeans.FieldDefinitionBuilder.field;

public class TestdatageneratorPersistence {
    @Getter
    private final EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(getClass().getName().hashCode())
            .exclude(field().named("id").ofType(String.class).get())
            .exclude(field().named("version").ofType(Long.class).get())
            .build();
    public BookingEntity.BookingEntityBuilder newBookingEntity() {
        return random.nextObject(BookingEntity.BookingEntityBuilder.class);
    }

    public BookingDayEntity.BookingDayEntityBuilder newBookingDayEntity() {
        return random.nextObject(BookingDayEntity.BookingDayEntityBuilder.class);
    }

}

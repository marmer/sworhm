package io.github.marmer.sworhm.testutils.testdata;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public interface ValueProvider extends Extension, BeforeEachCallback {
    void reset();

    long nextLong();

    BigDecimal nextBigDecimal();

    String nextString();

    LocalDate nextLocalDate();

    LocalDateTime nextLocalDateTime();

    int nextInt();

    <T extends Enum<?>> T nextEnumOf(Class<T> enumType);

    boolean nextBoolean();

    URI nextURI();

    @Override
    default void beforeEach(final ExtensionContext context) {
        reset();
    }

    LocalTime nextLocalTime();
}
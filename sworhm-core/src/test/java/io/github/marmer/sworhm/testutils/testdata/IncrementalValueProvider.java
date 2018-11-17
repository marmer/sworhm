package io.github.marmer.sworhm.testutils.testdata;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class IncrementalValueProvider implements ValueProvider {
    private static final long DEFAULT_BASE = 1;
    private final long initialBaseValue;
    private long currentBaseValue;
    private long increment = 1;

    public IncrementalValueProvider() {
        this(DEFAULT_BASE);
    }

    public IncrementalValueProvider(final long initialBaseValue) {
        this.initialBaseValue = initialBaseValue;
        this.currentBaseValue = initialBaseValue;
    }

    @Override
    public void reset() {
        currentBaseValue = initialBaseValue;
    }

    public IncrementalValueProvider withIncrement(final long increment) {
        this.increment = increment;

        return this;
    }

    @Override
    public long nextLong() {
        final long current = this.currentBaseValue;
        this.currentBaseValue += increment;
        return current;
    }

    @Override
    public BigDecimal nextBigDecimal() {
        return BigDecimal.valueOf(nextLong()).setScale(2);
    }

    @Override
    public String nextString() {
        return String.valueOf(nextLong());
    }

    @Override
    public LocalDate nextLocalDate() {
        return LocalDate.of(2000, 1, 1).plusDays(nextInt());
    }

    @Override
    public LocalDateTime nextLocalDateTime() {
        return LocalDateTime.of(2000, 1, 1, 15, 30).plusDays(nextInt());
    }

    @Override
    public int nextInt() {
        return (int) nextLong();
    }

    @Override
    public <T extends Enum<?>> T nextEnumOf(final Class<T> enumType) {
        final T[] enumConstants = enumType.getEnumConstants();

        return isEmpty(enumConstants) ?
                null :
                enumConstants[nextInt() % enumConstants.length];
    }

    private <T> boolean isEmpty(final T[] enumConstants) {
        return enumConstants == null || enumConstants.length == 0;
    }

    @Override
    public boolean nextBoolean() {
        final long base = nextLong();

        return Math.abs((base % 2)) == 0;
    }

    @Override
    public URI nextURI() {
        return URI.create("scheme:" + nextInt());
    }

    @Override
    public LocalTime nextLocalTime() {
        return nextLocalDateTime().toLocalTime();
    }

}
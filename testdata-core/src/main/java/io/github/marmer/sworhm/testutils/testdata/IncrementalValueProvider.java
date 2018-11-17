package io.github.marmer.sworhm.testutils.testdata;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;

/**
 * Provides Values based on an incrementing number. By default the counting starts based on number 1 and increments it
 * each time by 1.
 */
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

    /**
     * You can choose and change the increment.
     * <p>
     * Choose the increment whisely. Otherwise some types may produce each time the same value.
     * If you choose 2 for example {@link #nextBoolean()} will choose the same value each time. The same may happen
     * on other datatypes with a small amount of possible values.
     *
     * @param increment The increment size.
     * @return this.
     */
    public IncrementalValueProvider withIncrement(final long increment) {
        this.increment = increment;

        return this;
    }

    @Override
    public void reset() {
        currentBaseValue = initialBaseValue;
    }

    @Override
    public boolean nextBoolean() {
        return nextLong() % 2 == 1;
    }

    @Override
    public byte nextByte() {
        return (byte) nextLong();
    }

    @Override
    public char nextChar() {
        return (char) nextLong();
    }

    @Override
    public short nextShort() {
        return (short) nextLong();
    }

    @Override
    public int nextInt() {
        return (int) nextLong();
    }

    @Override
    public long nextLong() {
        final long current = this.currentBaseValue;
        this.currentBaseValue += increment;
        return current;
    }

    @Override
    public float nextFloat() {
        return (float) nextDouble();
    }

    @Override
    public double nextDouble() {
        final double next = nextLong();
        return next + ((next + 1) / 10);
    }

    @Override
    public String nextString() {
        return Long.toString(nextLong());
    }

    @Override
    public BigDecimal nextBigDecimal() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public BigInteger nextBigInteger() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public LocalTime nextLocalTime() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public LocalDate nextLocalDate() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public LocalDateTime nextLocalDateTime() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public ZonedDateTime nextZonedDateTime() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public Year nextYear() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public Month nextMonth() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public YearMonth nextYearMonth() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public MonthDay nextMonthDay() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public DayOfWeek nextDayOfWeek() {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public <T extends Enum<?>> T nextEnumOf(final Class<T> enumType) {
        // TODO: marmer implement me
        return null;
    }

    @Override
    public URI nextURI() {
        // TODO: marmer implement me
        return null;
    }


}
package io.github.marmer.sworhm.testutils.testdata;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URI;
import java.time.*;

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
        return BigDecimal.valueOf(nextFloat()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigInteger nextBigInteger() {
        return BigInteger.valueOf(nextLong());
    }

    @Override
    public LocalTime nextLocalTime() {
        return LocalTime.of(0, 0)
                .plusMinutes(nextLong() - 1);
    }

    @Override
    public LocalDate nextLocalDate() {
        final long val = nextLong();
        return LocalDate.of(2000, 1, 1)
                .plusDays(val - 1);
    }

    @Override
    public LocalDateTime nextLocalDateTime() {
        final long val = nextLong();
        return LocalDateTime.of(2000, 1, 1, 0, 0)
                .plusDays(val - 1)
                .plusMinutes(val - 1);
    }

    @Override
    public ZonedDateTime nextZonedDateTime() {
        return ZonedDateTime.of(nextLocalDateTime(), ZoneId.systemDefault());
    }

    @Override
    public Year nextYear() {
        return Year.of(1999 + nextInt());
    }

    @Override
    public Month nextMonth() {
        return Month.JANUARY.plus(nextLong() - 1);
    }

    @Override
    public YearMonth nextYearMonth() {
        return YearMonth.of(2000, Month.JANUARY).plusMonths(nextLong() - 1);
    }

    @Override
    public MonthDay nextMonthDay() {
        return MonthDay.from(nextLocalDate());
    }

    @Override
    public DayOfWeek nextDayOfWeek() {
        return DayOfWeek.MONDAY.plus(nextLong() - 1);
    }

    @Override
    public <T extends Enum<?>> T nextEnumOf(final Class<T> enumType) {
        return nextOf(enumType.getEnumConstants());
    }

    @Override
    public URI nextURI() {
        return URI.create("scheme:" + nextInt());
    }

    @Override
    public <T> T nextOf(final T... values) {
        return values == null || values.length == 0 ?
                null :
                values[(nextInt() - 1) % values.length];
    }


}
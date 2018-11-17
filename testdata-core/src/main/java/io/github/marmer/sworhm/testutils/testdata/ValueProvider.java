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
 * Provides Values of different types.
 */
public interface ValueProvider {
    void reset();

    boolean nextBoolean();

    byte nextByte();

    char nextChar();

    short nextShort();

    int nextInt();

    long nextLong();

    float nextFloat();

    double nextDouble();

    String nextString();

    BigDecimal nextBigDecimal();

    BigInteger nextBigInteger();

    LocalTime nextLocalTime();

    LocalDate nextLocalDate();

    LocalDateTime nextLocalDateTime();

    ZonedDateTime nextZonedDateTime();

    Year nextYear();

    Month nextMonth();

    YearMonth nextYearMonth();

    MonthDay nextMonthDay();

    DayOfWeek nextDayOfWeek();

    <T extends Enum<?>> T nextEnumOf(Class<T> enumType);

    URI nextURI();
}
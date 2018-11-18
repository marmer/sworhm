package io.github.marmer.sworhm.testutils.testdata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URI;
import java.time.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class IncrementalValueProviderTest {
    private static Stream<Arguments> defaultIncrements() {
        return Stream.of(
                arguments("nextLong",
                        new Object[]{1L, 2L, 3L},
                        (Function<IncrementalValueProvider, Long>) IncrementalValueProvider::nextLong)
                , arguments("nextBoolean",
                        new Object[]{true, false, true},
                        (Function<IncrementalValueProvider, Boolean>) IncrementalValueProvider::nextBoolean)
                , arguments("nextByte",
                        new Object[]{(byte) 1, (byte) 2, (byte) 3},
                        (Function<IncrementalValueProvider, Byte>) IncrementalValueProvider::nextByte)
                , arguments("nextChar",
                        new Object[]{(char) 1, (char) 2, (char) 3},
                        (Function<IncrementalValueProvider, Character>) IncrementalValueProvider::nextChar)
                , arguments("nextShort",
                        new Object[]{(short) 1, (short) 2, (short) 3},
                        (Function<IncrementalValueProvider, Short>) IncrementalValueProvider::nextShort)
                , arguments("nextInt",
                        new Object[]{1, 2, 3},
                        (Function<IncrementalValueProvider, Integer>) IncrementalValueProvider::nextInt)
                , arguments("nextFloat",
                        new Object[]{1f, 2f, 3f},
                        (Function<IncrementalValueProvider, Float>) IncrementalValueProvider::nextFloat)
                , arguments("nextDouble",
                        new Object[]{1d, 2d, 3d},
                        (Function<IncrementalValueProvider, Double>) IncrementalValueProvider::nextDouble)
                , arguments("nextString",
                        new Object[]{"1", "2", "3"},
                        (Function<IncrementalValueProvider, String>) IncrementalValueProvider::nextString)
                , arguments("nextBigDecimal",
                        new Object[]{
                                BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(3).setScale(2, RoundingMode.HALF_UP)},
                        (Function<IncrementalValueProvider, BigDecimal>) IncrementalValueProvider::nextBigDecimal)
                , arguments("nextBigInteger",
                        new Object[]{
                                BigInteger.valueOf(1),
                                BigInteger.valueOf(2),
                                BigInteger.valueOf(3)},
                        (Function<IncrementalValueProvider, BigInteger>) IncrementalValueProvider::nextBigInteger)
                , arguments("nextLocalDateTime",
                        new Object[]{
                                LocalDateTime.of(2000, 1, 1, 0, 0),
                                LocalDateTime.of(2000, 1, 2, 0, 1),
                                LocalDateTime.of(2000, 1, 3, 0, 2)},
                        (Function<IncrementalValueProvider, LocalDateTime>) IncrementalValueProvider::nextLocalDateTime)
                , arguments("nextLocalDate",
                        new Object[]{
                                LocalDate.of(2000, 1, 1),
                                LocalDate.of(2000, 1, 2),
                                LocalDate.of(2000, 1, 3)},
                        (Function<IncrementalValueProvider, LocalDate>) IncrementalValueProvider::nextLocalDate)
                , arguments("nextLocalTime",
                        new Object[]{
                                LocalTime.of(0, 0),
                                LocalTime.of(0, 1),
                                LocalTime.of(0, 2)},
                        (Function<IncrementalValueProvider, LocalTime>) IncrementalValueProvider::nextLocalTime)
                , arguments("nextZonedDateTime",
                        new Object[]{
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 0, 0), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 2, 0, 1), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 3, 0, 2), ZoneId.systemDefault())},
                        (Function<IncrementalValueProvider, ZonedDateTime>) IncrementalValueProvider::nextZonedDateTime)
                , arguments("nextYear",
                        new Object[]{
                                Year.of(2000),
                                Year.of(2001),
                                Year.of(2002)},
                        (Function<IncrementalValueProvider, Year>) IncrementalValueProvider::nextYear)
                , arguments("nextMonth",
                        new Object[]{
                                Month.JANUARY,
                                Month.FEBRUARY,
                                Month.MARCH,
                                Month.APRIL,
                                Month.MAY,
                                Month.JUNE,
                                Month.JULY,
                                Month.AUGUST,
                                Month.SEPTEMBER,
                                Month.OCTOBER,
                                Month.NOVEMBER,
                                Month.DECEMBER,
                                Month.JANUARY},
                        (Function<IncrementalValueProvider, Month>) IncrementalValueProvider::nextMonth)
                , arguments("nextYear",
                        new Object[]{
                                Year.of(2000),
                                Year.of(2001),
                                Year.of(2002)},
                        (Function<IncrementalValueProvider, Year>) IncrementalValueProvider::nextYear)
                , arguments("nextYearMonth",
                        new Object[]{
                                YearMonth.of(2000, Month.JANUARY),
                                YearMonth.of(2000, Month.FEBRUARY),
                                YearMonth.of(2000, Month.MARCH),
                                YearMonth.of(2000, Month.APRIL),
                                YearMonth.of(2000, Month.MAY),
                                YearMonth.of(2000, Month.JUNE),
                                YearMonth.of(2000, Month.JULY),
                                YearMonth.of(2000, Month.AUGUST),
                                YearMonth.of(2000, Month.SEPTEMBER),
                                YearMonth.of(2000, Month.OCTOBER),
                                YearMonth.of(2000, Month.NOVEMBER),
                                YearMonth.of(2000, Month.DECEMBER),
                                YearMonth.of(2001, Month.JANUARY)},
                        (Function<IncrementalValueProvider, YearMonth>) IncrementalValueProvider::nextYearMonth)
                , arguments("nextMonthDay",
                        new Object[]{
                                MonthDay.of(Month.JANUARY, 1),
                                MonthDay.of(Month.JANUARY, 2),
                                MonthDay.of(Month.JANUARY, 3),
                                MonthDay.of(Month.JANUARY, 4),
                                MonthDay.of(Month.JANUARY, 5),
                                MonthDay.of(Month.JANUARY, 6),
                                MonthDay.of(Month.JANUARY, 7),
                                MonthDay.of(Month.JANUARY, 8),
                                MonthDay.of(Month.JANUARY, 9),
                                MonthDay.of(Month.JANUARY, 10),
                                MonthDay.of(Month.JANUARY, 11),
                                MonthDay.of(Month.JANUARY, 12),
                                MonthDay.of(Month.JANUARY, 13),
                                MonthDay.of(Month.JANUARY, 14),
                                MonthDay.of(Month.JANUARY, 15),
                                MonthDay.of(Month.JANUARY, 16),
                                MonthDay.of(Month.JANUARY, 17),
                                MonthDay.of(Month.JANUARY, 18),
                                MonthDay.of(Month.JANUARY, 19),
                                MonthDay.of(Month.JANUARY, 20),
                                MonthDay.of(Month.JANUARY, 21),
                                MonthDay.of(Month.JANUARY, 22),
                                MonthDay.of(Month.JANUARY, 23),
                                MonthDay.of(Month.JANUARY, 24),
                                MonthDay.of(Month.JANUARY, 25),
                                MonthDay.of(Month.JANUARY, 26),
                                MonthDay.of(Month.JANUARY, 27),
                                MonthDay.of(Month.JANUARY, 28),
                                MonthDay.of(Month.JANUARY, 29),
                                MonthDay.of(Month.JANUARY, 30),
                                MonthDay.of(Month.JANUARY, 31),
                                MonthDay.of(Month.FEBRUARY, 1)},
                        (Function<IncrementalValueProvider, MonthDay>) IncrementalValueProvider::nextMonthDay)
                , arguments("nextDayOfWeek",
                        new Object[]{
                                DayOfWeek.MONDAY,
                                DayOfWeek.TUESDAY,
                                DayOfWeek.WEDNESDAY,
                                DayOfWeek.THURSDAY,
                                DayOfWeek.FRIDAY,
                                DayOfWeek.SATURDAY,
                                DayOfWeek.SUNDAY,
                                DayOfWeek.MONDAY},
                        (Function<IncrementalValueProvider, DayOfWeek>) IncrementalValueProvider::nextDayOfWeek)
                , arguments("nextEnumOf",
                        new Object[]{
                                SampleEnum.FIRST,
                                SampleEnum.SECOND,
                                SampleEnum.THIRD,
                                SampleEnum.FIRST},
                        (Function<IncrementalValueProvider, SampleEnum>) provider -> provider.nextEnumOf(SampleEnum.class))
                , arguments("nextOf",
                        new Object[]{
                                "1",
                                "2",
                                "3",
                                "1"},
                        (Function<IncrementalValueProvider, String>) provider -> provider.nextOf("1", "2", "3"))
                , arguments("nextOf",
                        new Object[]{
                                1,
                                2,
                                3,
                                4,
                                1},
                        (Function<IncrementalValueProvider, Integer>) provider -> provider.nextOf(1, 2, 3, 4))
                , arguments("nextOf",
                        new Object[]{
                                null,
                                null},
                        (Function<IncrementalValueProvider, Integer>) provider -> provider.nextOf())
                , arguments("nextOf",
                        new Object[]{
                                null,
                                null},
                        (Function<IncrementalValueProvider, Integer>) provider -> provider.nextOf(null))
                , arguments("nextURI",
                        new Object[]{
                                URI.create("scheme:1"),
                                URI.create("scheme:2"),
                                URI.create("scheme:3")},
                        (Function<IncrementalValueProvider, URI>) IncrementalValueProvider::nextURI)
        );
    }

    private static Stream<Arguments> negativeBase() {
        return Stream.of(
                arguments("nextLong",
                        new Object[]{-2L, -1L, 0L, 1L, 2L},
                        (Function<IncrementalValueProvider, Long>) IncrementalValueProvider::nextLong)
                , arguments("nextBoolean",
                        new Object[]{false, true, false},
                        (Function<IncrementalValueProvider, Boolean>) IncrementalValueProvider::nextBoolean)
                , arguments("nextByte",
                        new Object[]{(byte) -2, (byte) -1, (byte) 0, (byte) 1, (byte) 2},
                        (Function<IncrementalValueProvider, Byte>) IncrementalValueProvider::nextByte)
                , arguments("nextChar",
                        new Object[]{(char) -2, (char) -1, (char) 0, (char) 1, (char) 2},
                        (Function<IncrementalValueProvider, Character>) IncrementalValueProvider::nextChar)
                , arguments("nextShort",
                        new Object[]{(short) -2, (short) -1, (short) 0, (short) 1, (short) 2},
                        (Function<IncrementalValueProvider, Short>) IncrementalValueProvider::nextShort)
                , arguments("nextInt",
                        new Object[]{-2, -1, 0, 1, 2},
                        (Function<IncrementalValueProvider, Integer>) IncrementalValueProvider::nextInt)
                , arguments("nextFloat",
                        new Object[]{-2f, -1f, 0f, 1f, 2f},
                        (Function<IncrementalValueProvider, Float>) IncrementalValueProvider::nextFloat)
                , arguments("nextDouble",
                        new Object[]{-2d, -1d, 0d, 1d, 2d},
                        (Function<IncrementalValueProvider, Double>) IncrementalValueProvider::nextDouble)
                , arguments("nextString",
                        new Object[]{"-2", "-1", "0", "1", "2"},
                        (Function<IncrementalValueProvider, String>) IncrementalValueProvider::nextString)
                , arguments("nextBigDecimal",
                        new Object[]{
                                BigDecimal.valueOf(-2d).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(-1d).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(0d).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(1d).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(2d).setScale(2, RoundingMode.HALF_UP)},
                        (Function<IncrementalValueProvider, BigDecimal>) IncrementalValueProvider::nextBigDecimal)
                , arguments("nextBigInteger",
                        new Object[]{
                                BigInteger.valueOf(-2),
                                BigInteger.valueOf(-1),
                                BigInteger.valueOf(0),
                                BigInteger.valueOf(1),
                                BigInteger.valueOf(2)},
                        (Function<IncrementalValueProvider, BigInteger>) IncrementalValueProvider::nextBigInteger)
                , arguments("nextLocalDateTime",
                        new Object[]{
                                LocalDateTime.of(1999, 12, 28, 23, 57),
                                LocalDateTime.of(1999, 12, 29, 23, 58),
                                LocalDateTime.of(1999, 12, 30, 23, 59),
                                LocalDateTime.of(2000, 1, 1, 0, 0),
                                LocalDateTime.of(2000, 1, 2, 0, 1)},
                        (Function<IncrementalValueProvider, LocalDateTime>) IncrementalValueProvider::nextLocalDateTime)
                , arguments("nextLocalDate",
                        new Object[]{
                                LocalDate.of(1999, 12, 29),
                                LocalDate.of(1999, 12, 30),
                                LocalDate.of(1999, 12, 31),
                                LocalDate.of(2000, 1, 1),
                                LocalDate.of(2000, 1, 2)},
                        (Function<IncrementalValueProvider, LocalDate>) IncrementalValueProvider::nextLocalDate)
                , arguments("nextLocalTime",
                        new Object[]{
                                LocalTime.of(23, 57),
                                LocalTime.of(23, 58),
                                LocalTime.of(23, 59),
                                LocalTime.of(0, 0),
                                LocalTime.of(0, 1)},
                        (Function<IncrementalValueProvider, LocalTime>) IncrementalValueProvider::nextLocalTime)
                , arguments("nextZonedDateTime",
                        new Object[]{
                                ZonedDateTime.of(LocalDateTime.of(1999, 12, 28, 23, 57), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(1999, 12, 29, 23, 58), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(1999, 12, 30, 23, 59), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 0, 0), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 2, 0, 1), ZoneId.systemDefault())
                        },
                        (Function<IncrementalValueProvider, ZonedDateTime>) IncrementalValueProvider::nextZonedDateTime)
                , arguments("nextYear",
                        new Object[]{
                                Year.of(1997),
                                Year.of(1998),
                                Year.of(1999),
                                Year.of(2000),
                                Year.of(2001)},
                        (Function<IncrementalValueProvider, Year>) IncrementalValueProvider::nextYear)
                , arguments("nextMonth",
                        new Object[]{
                                Month.OCTOBER,
                                Month.NOVEMBER,
                                Month.DECEMBER,
                                Month.JANUARY,
                                Month.FEBRUARY,},
                        (Function<IncrementalValueProvider, Month>) IncrementalValueProvider::nextMonth)
                , arguments("nextYear",
                        new Object[]{
                                Year.of(1997),
                                Year.of(1998),
                                Year.of(1999),
                                Year.of(2000),
                                Year.of(2001)},
                        (Function<IncrementalValueProvider, Year>) IncrementalValueProvider::nextYear)
                , arguments("nextYearMonth",
                        new Object[]{
                                YearMonth.of(1999, Month.OCTOBER),
                                YearMonth.of(1999, Month.NOVEMBER),
                                YearMonth.of(1999, Month.DECEMBER),
                                YearMonth.of(2000, Month.JANUARY),
                                YearMonth.of(2000, Month.FEBRUARY),},
                        (Function<IncrementalValueProvider, YearMonth>) IncrementalValueProvider::nextYearMonth)
                , arguments("nextMonthDay",
                        new Object[]{
                                MonthDay.of(Month.DECEMBER, 29),
                                MonthDay.of(Month.DECEMBER, 30),
                                MonthDay.of(Month.DECEMBER, 31),
                                MonthDay.of(Month.JANUARY, 1),
                                MonthDay.of(Month.JANUARY, 2),
                                MonthDay.of(Month.JANUARY, 3)},
                        (Function<IncrementalValueProvider, MonthDay>) IncrementalValueProvider::nextMonthDay)
                , arguments("nextDayOfWeek",
                        new Object[]{
                                DayOfWeek.FRIDAY,
                                DayOfWeek.SATURDAY,
                                DayOfWeek.SUNDAY,
                                DayOfWeek.MONDAY,
                                DayOfWeek.TUESDAY},
                        (Function<IncrementalValueProvider, DayOfWeek>) IncrementalValueProvider::nextDayOfWeek)
                , arguments("nextEnumOf",
                        new Object[]{
                                SampleEnum.FIRST,
                                SampleEnum.SECOND,
                                SampleEnum.THIRD,
                                SampleEnum.FIRST},
                        (Function<IncrementalValueProvider, SampleEnum>) provider -> provider.nextEnumOf(SampleEnum.class))
                , arguments("nextOf",
                        new Object[]{
                                "1",
                                "2",
                                "3",
                                "1"},
                        (Function<IncrementalValueProvider, String>) provider -> provider.nextOf("1", "2", "3"))
                , arguments("nextOf",
                        new Object[]{
                                2,
                                3,
                                4,
                                1,
                                2},
                        (Function<IncrementalValueProvider, Integer>) provider -> provider.nextOf(1, 2, 3, 4))
                , arguments("nextURI",
                        new Object[]{
                                URI.create("scheme:-2"),
                                URI.create("scheme:-1"),
                                URI.create("scheme:0"),
                                URI.create("scheme:1"),
                                URI.create("scheme:2"),
                                URI.create("scheme:3")},
                        (Function<IncrementalValueProvider, URI>) IncrementalValueProvider::nextURI)
        );
    }

    private static Stream<Arguments> increment3() {
        return Stream.of(
                arguments("nextLong",
                        new Object[]{-2L, 1L, 4L},
                        (Function<IncrementalValueProvider, Long>) IncrementalValueProvider::nextLong)
                , arguments("nextBoolean",
                        new Object[]{false, true, false},
                        (Function<IncrementalValueProvider, Boolean>) IncrementalValueProvider::nextBoolean)
                , arguments("nextByte",
                        new Object[]{(byte) -2, (byte) 1, (byte) 4},
                        (Function<IncrementalValueProvider, Byte>) IncrementalValueProvider::nextByte)
                , arguments("nextChar",
                        new Object[]{(char) -2, (char) 1, (char) 4},
                        (Function<IncrementalValueProvider, Character>) IncrementalValueProvider::nextChar)
                , arguments("nextShort",
                        new Object[]{(short) -2, (short) 1, (short) 4},
                        (Function<IncrementalValueProvider, Short>) IncrementalValueProvider::nextShort)
                , arguments("nextInt",
                        new Object[]{-2, 1, 4},
                        (Function<IncrementalValueProvider, Integer>) IncrementalValueProvider::nextInt)
                , arguments("nextFloat",
                        new Object[]{-2f, 1f, 4f},
                        (Function<IncrementalValueProvider, Float>) IncrementalValueProvider::nextFloat)
                , arguments("nextDouble",
                        new Object[]{-2d, 1d, 4d},
                        (Function<IncrementalValueProvider, Double>) IncrementalValueProvider::nextDouble)
                , arguments("nextString",
                        new Object[]{"-2", "1", "4"},
                        (Function<IncrementalValueProvider, String>) IncrementalValueProvider::nextString)
                , arguments("nextBigDecimal",
                        new Object[]{
                                BigDecimal.valueOf(-2d).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(1d).setScale(2, RoundingMode.HALF_UP),
                                BigDecimal.valueOf(4d).setScale(2, RoundingMode.HALF_UP)},
                        (Function<IncrementalValueProvider, BigDecimal>) IncrementalValueProvider::nextBigDecimal)
                , arguments("nextBigInteger",
                        new Object[]{
                                BigInteger.valueOf(-2),
                                BigInteger.valueOf(1),
                                BigInteger.valueOf(4)},
                        (Function<IncrementalValueProvider, BigInteger>) IncrementalValueProvider::nextBigInteger)
                , arguments("nextLocalDateTime",
                        new Object[]{
                                LocalDateTime.of(1999, 12, 28, 23, 57),
                                LocalDateTime.of(2000, 1, 1, 0, 0),
                                LocalDateTime.of(2000, 1, 4, 0, 3)},
                        (Function<IncrementalValueProvider, LocalDateTime>) IncrementalValueProvider::nextLocalDateTime)
                , arguments("nextLocalDate",
                        new Object[]{
                                LocalDate.of(1999, 12, 29),
                                LocalDate.of(2000, 1, 1),
                                LocalDate.of(2000, 1, 4)},
                        (Function<IncrementalValueProvider, LocalDate>) IncrementalValueProvider::nextLocalDate)
                , arguments("nextLocalTime",
                        new Object[]{
                                LocalTime.of(23, 57),
                                LocalTime.of(0, 0),
                                LocalTime.of(0, 3)},
                        (Function<IncrementalValueProvider, LocalTime>) IncrementalValueProvider::nextLocalTime)
                , arguments("nextZonedDateTime",
                        new Object[]{
                                ZonedDateTime.of(LocalDateTime.of(1999, 12, 28, 23, 57), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 0, 0), ZoneId.systemDefault()),
                                ZonedDateTime.of(LocalDateTime.of(2000, 1, 4, 0, 3), ZoneId.systemDefault())
                        },
                        (Function<IncrementalValueProvider, ZonedDateTime>) IncrementalValueProvider::nextZonedDateTime)
                , arguments("nextYear",
                        new Object[]{
                                Year.of(1997),
                                Year.of(2000),
                                Year.of(2003)},
                        (Function<IncrementalValueProvider, Year>) IncrementalValueProvider::nextYear)
                , arguments("nextMonth",
                        new Object[]{
                                Month.OCTOBER,
                                Month.JANUARY,
                                Month.APRIL,},
                        (Function<IncrementalValueProvider, Month>) IncrementalValueProvider::nextMonth)
                , arguments("nextYear",
                        new Object[]{
                                Year.of(1997),
                                Year.of(2000),
                                Year.of(2003)},
                        (Function<IncrementalValueProvider, Year>) IncrementalValueProvider::nextYear)
                , arguments("nextYearMonth",
                        new Object[]{
                                YearMonth.of(1999, Month.OCTOBER),
                                YearMonth.of(2000, Month.JANUARY),
                                YearMonth.of(2000, Month.APRIL),},
                        (Function<IncrementalValueProvider, YearMonth>) IncrementalValueProvider::nextYearMonth)
                , arguments("nextMonthDay",
                        new Object[]{
                                MonthDay.of(Month.DECEMBER, 29),
                                MonthDay.of(Month.JANUARY, 1),
                                MonthDay.of(Month.JANUARY, 4)},
                        (Function<IncrementalValueProvider, MonthDay>) IncrementalValueProvider::nextMonthDay)
                , arguments("nextDayOfWeek",
                        new Object[]{
                                DayOfWeek.FRIDAY,
                                DayOfWeek.MONDAY,
                                DayOfWeek.THURSDAY},
                        (Function<IncrementalValueProvider, DayOfWeek>) IncrementalValueProvider::nextDayOfWeek)
                , arguments("nextEnumOf",
                        new Object[]{
                                SampleEnum.FIRST,
                                SampleEnum.FIRST,
                                SampleEnum.FIRST},
                        (Function<IncrementalValueProvider, SampleEnum>) provider -> provider.nextEnumOf(SampleEnum.class))
                , arguments("nextOf",
                        new Object[]{
                                "1",
                                "1",
                                "1"},
                        (Function<IncrementalValueProvider, String>) provider -> provider.nextOf("1", "2", "3"))
                , arguments("nextOf",
                        new Object[]{
                                2,
                                1,
                                4},
                        (Function<IncrementalValueProvider, Integer>) provider -> provider.nextOf(1, 2, 3, 4))
                , arguments("nextURI",
                        new Object[]{
                                URI.create("scheme:-2"),
                                URI.create("scheme:1"),
                                URI.create("scheme:4")},
                        (Function<IncrementalValueProvider, URI>) IncrementalValueProvider::nextURI)
        );
    }

    @ParameterizedTest(name = "{0}: {1}")
    @MethodSource("defaultIncrements")
    @DisplayName("Test with default increments and base")
    public void defaultBaseAndIncremtnTests(final String description, final Object[] expectedValues, final Function<IncrementalValueProvider, ?> method)
            throws Exception {
        // Preparation
        final IncrementalValueProvider underTest = new IncrementalValueProvider();

        // Execution
        final Object[] values = Stream.generate(() -> method.apply(underTest))
                .limit(expectedValues.length)
                .toArray();

        // Assertion
        assertThat(values, arrayContaining(expectedValues));
    }

    @ParameterizedTest(name = "{0}: {1}")
    @MethodSource("negativeBase")
    @DisplayName("Test with base -2 and default increment")
    public void nevativeBase(final String description, final Object[] expectedValues, final Function<IncrementalValueProvider, ?> method)
            throws Exception {
        // Preparation
        final IncrementalValueProvider underTest = new IncrementalValueProvider(-2);

        // Execution
        final Object[] values = Stream.generate(() -> method.apply(underTest))
                .limit(expectedValues.length)
                .toArray();

        // Assertion
        assertThat(values, arrayContaining(expectedValues));
    }

    @ParameterizedTest(name = "{0}: {1}")
    @MethodSource("increment3")
    @DisplayName("Test with default base and default an increment of 3")
    public void increment3(final String description, final Object[] expectedValues, final Function<IncrementalValueProvider, ?> method)
            throws Exception {
        // Preparation
        final IncrementalValueProvider underTest = new IncrementalValueProvider(-2).withIncrement(3);

        // Execution
        final Object[] values = Stream.generate(() -> method.apply(underTest))
                .limit(expectedValues.length)
                .toArray();

        // Assertion
        assertThat(values, arrayContaining(expectedValues));
    }

    // TODO: marmer 18.11.2018 negative increment
    enum SampleEnum {
        FIRST,
        SECOND,
        THIRD
    }
}
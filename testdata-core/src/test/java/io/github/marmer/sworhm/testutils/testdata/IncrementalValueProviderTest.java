package io.github.marmer.sworhm.testutils.testdata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
                        new Object[]{1.2f, 2.3f, 3.4f},
                        (Function<IncrementalValueProvider, Float>) IncrementalValueProvider::nextFloat)
                , arguments("nextDouble",
                        new Object[]{1.2d, 2.3d, 3.4d},
                        (Function<IncrementalValueProvider, Double>) IncrementalValueProvider::nextDouble)
                , arguments("nextString",
                        new Object[]{"1", "2", "3"},
                        (Function<IncrementalValueProvider, String>) IncrementalValueProvider::nextString)
        );
    }

    @ParameterizedTest
    @MethodSource("defaultIncrements")
    @DisplayName("Test with default increments and base")
    public void defaultIncremtnTests(final String description, final Object[] expectedValues, final Function<IncrementalValueProvider, ?> method)
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

}
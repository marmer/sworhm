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
                        (Function<IncrementalValueProvider, Long>) IncrementalValueProvider::nextLong
                ));
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
package io.github.marmer.sworhm.testutils.testdata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class IncrementalValueProviderTest {
    @Test
    public void testNextLong_InitializedWithOneAndAnIncrementOf1_ShouldServeValuesWithAnIncrementOfOne()
            throws Exception {
        // Preparation
        final IncrementalValueProvider underTest = new IncrementalValueProvider();

        // Execution
        final List<Long> values = asList(
                underTest.nextLong(),
                underTest.nextLong(),
                underTest.nextLong());

        // Assertion
        assertThat(values, contains(1L, 2L, 3L));
    }

}
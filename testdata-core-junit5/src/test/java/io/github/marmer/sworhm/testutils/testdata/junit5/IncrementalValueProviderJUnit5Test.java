package io.github.marmer.sworhm.testutils.testdata.junit5;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.mock;

class IncrementalValueProviderJUnit5Test {

    @RegisterExtension
    final IncrementalValueProviderJUnit5 underTestInLifecycle = new IncrementalValueProviderJUnit5();

    @Test
    @DisplayName("Should reset to default base before each testmethod")
    public void testBeaforeEach_BeforeEachtCalled_ShouldResetValueProvider()
            throws Exception {
        // Preparation
        final IncrementalValueProviderJUnit5 underTest = new IncrementalValueProviderJUnit5();

        final ExtensionContext anyContext = mock(ExtensionContext.class);
        underTest.nextLong();
        underTest.nextLong();

        // Execution
        underTest.beforeEach(anyContext);

        // Assertion
        assertThat(asList(underTest.nextLong(), underTest.nextLong()), contains(1L, 2L));
    }

    @Test
    @DisplayName("Should reset to initial base before each testmethod")
    public void testBeaforeEach_BeforeEachCalledWithDifferentBaseValue_ShouldResetValueProvider()
            throws Exception {
        // Preparation
        final IncrementalValueProviderJUnit5 underTest = new IncrementalValueProviderJUnit5(5);

        final ExtensionContext anyContext = mock(ExtensionContext.class);
        underTest.nextLong();
        underTest.nextLong();

        // Execution
        underTest.beforeEach(anyContext);

        // Assertion
        assertThat(asList(underTest.nextLong(), underTest.nextLong()), contains(5L, 6L));
    }

    @RepeatedTest(3)
    @DisplayName("Junit lifecycle should work")
    public void testGetValueProvider_MultipleTimesCalled_BasedValueProviderShouldServeTheSameValuesInEachRun()
            throws Exception {
        // Preparation
        final ValueProvider valueProvider = underTestInLifecycle;

        // Execution
        final List<Long> results = asList(
                valueProvider.nextLong(),
                valueProvider.nextLong(),
                valueProvider.nextLong());
        // Assertion
        assertThat(results, contains(1L, 2L, 3L));
    }
}
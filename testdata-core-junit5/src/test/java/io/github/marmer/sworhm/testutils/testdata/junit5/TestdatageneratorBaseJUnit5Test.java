package io.github.marmer.sworhm.testutils.testdata.junit5;

import io.github.marmer.sworhm.testutils.testdata.ValueProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TestdatageneratorBaseJUnit5Test {

    @RegisterExtension
    private final TestdatageneratorBaseJUnit5 underTestInLifecycle = new TestdatageneratorBaseJUnit5();
    private TestdatageneratorBaseJUnit5 underTest;

    @Mock
    private ValueProvider valueProvider;

    @BeforeEach
    void setUp() {
        underTest = new TestdatageneratorBaseJUnit5(valueProvider);
    }

    @Test
    @DisplayName("Reset should be called before eacht test")
    public void testBeaforeEach_BeforeEachtCalled_ShouldResetValueProvider()
            throws Exception {
        // Preparation
        final ExtensionContext anyContext = mock(ExtensionContext.class);

        // Execution
        underTest.beforeEach(anyContext);

        // Assertion
        verify(valueProvider).reset();
    }

    @RepeatedTest(3)
    @DisplayName("Junit lifecycle should work")
    public void testGetValueProvider_MultipleTimesCalled_BasedValueProviderShouldServeTheSameValuesInEachRun()
            throws Exception {
        // Preparation
        final ValueProvider valueProvider = underTestInLifecycle.getValueProvider();

        // Execution
        final List<Long> results = asList(
                valueProvider.nextLong(),
                valueProvider.nextLong(),
                valueProvider.nextLong());
        // Assertion
        assertThat(results, contains(1L, 2L, 3L));
    }
}
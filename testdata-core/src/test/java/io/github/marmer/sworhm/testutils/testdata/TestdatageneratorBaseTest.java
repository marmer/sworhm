package io.github.marmer.sworhm.testutils.testdata;

import lombok.var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class TestdatageneratorBaseTest {
    public enum SampleEnum {
        FIRST, SECOND
    }

    @Test
    @DisplayName("Values provided shold come from the the given Value Provider")
    public void testConstructor_CustomValueProviderGiven_ShouldUseValuesFromGivenValueProvider() {
        // Preparation
        final ValueProvider customValueProvider = mock(ValueProvider.class, "customValueProvider");

        final TestdatageneratorBase underTest = new SampleImpl(customValueProvider);

        // Execution
        final ValueProvider result = underTest.getValueProvider();

        // Assertion
        assertThat(result, is(sameInstance(customValueProvider)));
    }

    @Test
    @DisplayName("Constructor call with default constructor should initialize with an incremental value provider based on 1 and an increment of 1 ")
    public void testGetValueProvider_DefaultConstructorUsed_ShouldServeAnExpectedValueProviderImplementation() {
        // Preparation
        final TestdatageneratorBase underTest = new SampleImpl();

        // Execution
        final var result = underTest.getValueProvider();

        // Assertion
        assertThat(result, is(instanceOf(IncrementalValueProvider.class)));
        assertThat(asList(result.nextInt(), result.nextInt(), result.nextInt()),
                contains(1, 2, 3));
    }

    @Test
    @DisplayName("Call of reset should reset the value provider")
    public void testReset_SimpleCall_ShouldResetTheValueProvider() {
        // Preparation
        final ValueProvider valueProvider = mock(ValueProvider.class);
        final TestdatageneratorBase underTest = new SampleImpl(valueProvider);

        // Execution
        underTest.reset();

        // Assertion
        verify(valueProvider).reset();
    }

    @Test
    @DisplayName("call for nextEnumOf should be delegated to valueProvider")
    public void testNextEnumOf_MethodCall_ShouldDelegateToValueProvider() {
        // Preparation
        final ValueProvider valueProvider = mock(ValueProvider.class);
        when(valueProvider.nextEnumOf(SampleEnum.class)).thenReturn(SampleEnum.SECOND);
        final TestdatageneratorBase underTest = new SampleImpl(valueProvider);

        // Execution
        final SampleEnum result = underTest.nextEnumOf(SampleEnum.class);

        // Assertion
        assertThat(result, is(SampleEnum.SECOND));
    }

    public static class SampleImpl extends TestdatageneratorBase {
        public SampleImpl(final ValueProvider valueProvider) {
            super(valueProvider);
        }

        public SampleImpl() {
            super();
        }
    }
}
package io.github.marmer.sworhm.testutils.testdata;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class TestdatageneratorBase extends TestWatcher {
    private final ValueProvider valueProvider;

    public TestdatageneratorBase(final ValueProvider valueProvider) {
        this.valueProvider = valueProvider;
    }

    public TestdatageneratorBase() {
        this(new IncrementalValueProvider(1).withIncrement(1));
    }

    @Override
    protected void starting(final Description description) {
        this.valueProvider.reset();
    }

    public ValueProvider getValueProvider() {
        return valueProvider;
    }

    public <T extends Enum<?>> T nextEnumOf(final Class<T> enumType) {
        return getValueProvider().nextEnumOf(enumType);
    }
}

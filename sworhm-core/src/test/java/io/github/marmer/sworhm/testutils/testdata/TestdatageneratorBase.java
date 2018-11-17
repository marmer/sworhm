package io.github.marmer.sworhm.testutils.testdata;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

public abstract class TestdatageneratorBase implements Extension, BeforeEachCallback {
    private final ValueProvider valueProvider;

    public TestdatageneratorBase(final ValueProvider valueProvider) {
        this.valueProvider = valueProvider;
    }

    public TestdatageneratorBase() {
        this(new IncrementalValueProvider(1).withIncrement(1));
    }

    public ValueProvider getValueProvider() {
        return valueProvider;
    }

    public <T extends Enum<?>> T nextEnumOf(final Class<T> enumType) {
        return getValueProvider().nextEnumOf(enumType);
    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        valueProvider.reset();
    }
}

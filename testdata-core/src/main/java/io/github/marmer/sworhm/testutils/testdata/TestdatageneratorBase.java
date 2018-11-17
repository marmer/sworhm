package io.github.marmer.sworhm.testutils.testdata;


public abstract class TestdatageneratorBase {
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
}

package io.github.marmer.sworhm.testutils.testdata;


/**
 * Base class for testdatagenerators. An implementation would use a {@link ValueProvider} to fill complex objects with
 * state. By default, an {@link IncrementalValueProvider} is used, but this may change on later versions.
 * <p>
 * An Implementation could either be done as Junit Jupiter Extension, JUnit4 Rule or an equivalent of other testing
 * frameworks or do a {@link #reset()} somehow else before or after each test lifecycle to get comparable results.
 * <p>
 * In Addition you should add a methods to provide Objects (or builder) populated by values of the {@link ValueProvider}
 * for some real testdata. In your testcode you then only have to change values relevant for your testcase.
 */
public abstract class TestdatageneratorBase {
    private final ValueProvider valueProvider;

    /**
     * Takes the value provider, the values are based on.
     *
     * @param valueProvider ValueProvider.
     */
    public TestdatageneratorBase(final ValueProvider valueProvider) {
        this.valueProvider = valueProvider;
    }

    /**
     * Default Constructor. Initializes the instance with an Incremental valueprovider based on an initial Value of 1
     * and an Increment of 1.
     */
    public TestdatageneratorBase() {
        this(new IncrementalValueProvider(1).withIncrement(1));
    }

    /**
     * Returns the value provider used internally. This can be helpful to get some more base values withing you test.
     *
     * @return The value provider used internally.
     */
    public ValueProvider getValueProvider() {
        return valueProvider;
    }

    /**
     * This is a convenience method which delegates to {@link ValueProvider#nextEnumOf(Class)} because you may need it
     * pretty often. So you donn't need (but may) create a method for each of you enums.
     *
     * @param enumType Classtype of the enums from which to choose a value from.
     * @param <T>      Type of the enums from which to choose a value from.
     * @return a value.
     */
    public <T extends Enum<?>> T nextEnumOf(final Class<T> enumType) {
        return getValueProvider().nextEnumOf(enumType);
    }

    /**
     * Performs a reset on the {@link ValueProvider}. May be called before or after each test-lifecycle.
     */
    public void reset() {
        getValueProvider().reset();
    }
}

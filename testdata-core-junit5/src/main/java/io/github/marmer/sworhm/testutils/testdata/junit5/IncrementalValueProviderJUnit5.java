package io.github.marmer.sworhm.testutils.testdata.junit5;

import io.github.marmer.sworhm.testutils.testdata.IncrementalValueProvider;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class IncrementalValueProviderJUnit5 extends IncrementalValueProvider implements Extension, BeforeEachCallback {
    public IncrementalValueProviderJUnit5() {
        super();
    }

    public IncrementalValueProviderJUnit5(final long initialBaseValue) {
        super(initialBaseValue);
    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        reset();
    }
}
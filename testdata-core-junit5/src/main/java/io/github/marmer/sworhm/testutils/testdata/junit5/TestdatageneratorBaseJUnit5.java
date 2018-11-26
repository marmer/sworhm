package io.github.marmer.sworhm.testutils.testdata.junit5;

import io.github.marmer.sworhm.testutils.testdata.TestdatageneratorBase;
import io.github.marmer.sworhm.testutils.testdata.ValueProvider;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestdatageneratorBaseJUnit5 extends TestdatageneratorBase implements Extension, BeforeEachCallback {
    public TestdatageneratorBaseJUnit5(final ValueProvider valueProvider) {
        super(valueProvider);
    }

    public TestdatageneratorBaseJUnit5() {
        super();
    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        getValueProvider().reset();
    }
}

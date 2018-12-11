package io.github.marmer.sworhm.core.impl;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ExtendWith(MockitoExtension.class)
class RealSystemTimeServiceTest {
    @InjectMocks
    private RealSystemTimeService underTest;

    @Test
    @DisplayName("Methodcall should serve a date close to now")
    void testNow_MethodcallShouldServeADateCloseToNow()
            throws Exception {
        final LocalDateTime before = LocalDateTime.now();
        // Execution
        final var result = underTest.now();
        final LocalDateTime after = LocalDateTime.now();

        // Assertion
        assertThat(result, is(both(isAfterOrEqual(before)).and(isBeforeOrEqual(after))));
    }

    private TypeSafeMatcher<LocalDateTime> isAfterOrEqual(final LocalDateTime before) {
        return new TypeSafeMatcher<>() {
            @Override
            protected boolean matchesSafely(final LocalDateTime item) {
                return before.isBefore(item) || before.equals(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("A after before ").appendValue(before);
            }
        };
    }

    private TypeSafeMatcher<LocalDateTime> isBeforeOrEqual(final LocalDateTime after) {
        return new TypeSafeMatcher<>() {
            @Override
            protected boolean matchesSafely(final LocalDateTime item) {
                return after.isAfter(item) || after.equals(item);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("A after after ").appendValue(after);
            }
        };
    }
}
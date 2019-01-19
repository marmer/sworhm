package org.springframework.http;

import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;
import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

@BasedOn(BookingEntity.class)
public class ResponseEntityMatcher extends TypeSafeMatcher<ResponseEntity> {
    private final BeanPropertyMatcher<ResponseEntity> beanPropertyMatcher;

    public ResponseEntityMatcher() {
        beanPropertyMatcher = new BeanPropertyMatcher<ResponseEntity>(ResponseEntity.class);
    }

    public static ResponseEntityMatcher isResponseEntity() {
        return new ResponseEntityMatcher();
    }

    public ResponseEntityMatcher withHeaders(final Matcher<HttpHeaders> matcher) {
        beanPropertyMatcher.with("headers", matcher);
        return this;
    }

    public ResponseEntityMatcher withHeaders(final HttpHeaders value) {
        beanPropertyMatcher.with("headers", Matchers.equalTo(value));
        return this;
    }

    public ResponseEntityMatcher withBody(final Matcher<?> matcher) {
        beanPropertyMatcher.with("body", matcher);
        return this;
    }

    public ResponseEntityMatcher withBody(final Object value) {
        beanPropertyMatcher.with("body", Matchers.equalTo(value));
        return this;
    }

    public ResponseEntityMatcher withStatusCode(final Matcher<HttpStatus> matcher) {
        beanPropertyMatcher.with("statusCode", matcher);
        return this;
    }

    public ResponseEntityMatcher withStatusCode(final HttpStatus value) {
        beanPropertyMatcher.with("statusCode", Matchers.equalTo(value));
        return this;
    }

    public ResponseEntityMatcher withStatusCodeValue(final Integer value) {
        beanPropertyMatcher.with("statusCodeValue", Matchers.equalTo(value));
        return this;
    }


    public ResponseEntityMatcher withStatusCodeValue(final Matcher<Integer> matcher) {
        beanPropertyMatcher.with("statusCodeValue", matcher);
        return this;
    }

    @Override
    public void describeTo(final Description description) {
        beanPropertyMatcher.describeTo(description);
    }

    @Override
    protected boolean matchesSafely(final ResponseEntity item) {
        return beanPropertyMatcher.matches(item);
    }

    @Override
    protected void describeMismatchSafely(final ResponseEntity item, final Description description) {
        beanPropertyMatcher.describeMismatch(item, description);
    }
}

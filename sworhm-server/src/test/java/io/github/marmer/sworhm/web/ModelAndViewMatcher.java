package io.github.marmer.sworhm.web;

import io.github.marmer.testutils.generators.beanmatcher.dependencies.BasedOn;
import io.github.marmer.testutils.generators.beanmatcher.dependencies.BeanPropertyMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.Map;

@BasedOn(ModelAndView.class)
public class ModelAndViewMatcher extends TypeSafeMatcher<ModelAndView> {
    private final BeanPropertyMatcher<ModelAndView> beanPropertyMatcher;

    public ModelAndViewMatcher() {
        beanPropertyMatcher = new BeanPropertyMatcher<>(ModelAndView.class);
    }

    public static ModelAndViewMatcher isModelAndView() {
        return new ModelAndViewMatcher();
    }

    public ModelAndViewMatcher withModel(final Matcher<?> matcher) {
        beanPropertyMatcher.with("model", matcher);
        return this;
    }

    public ModelAndViewMatcher withModel(final Map<?, ?> value) {
        beanPropertyMatcher.with("model", Matchers.equalTo(value));
        return this;
    }

    public ModelAndViewMatcher withModelMap(final Matcher<ModelMap> matcher) {
        beanPropertyMatcher.with("modelMap", matcher);
        return this;
    }

    public ModelAndViewMatcher withModelMap(final ModelMap value) {
        beanPropertyMatcher.with("modelMap", Matchers.equalTo(value));
        return this;
    }

    public ModelAndViewMatcher withStatus(final Matcher<HttpStatus> matcher) {
        beanPropertyMatcher.with("status", matcher);
        return this;
    }

    public ModelAndViewMatcher withStatus(final HttpStatus value) {
        beanPropertyMatcher.with("status", Matchers.equalTo(value));
        return this;
    }

    public ModelAndViewMatcher withView(final Matcher<View> matcher) {
        beanPropertyMatcher.with("view", matcher);
        return this;
    }

    public ModelAndViewMatcher withView(final View value) {
        beanPropertyMatcher.with("view", Matchers.equalTo(value));
        return this;
    }

    public ModelAndViewMatcher withViewName(final Matcher<String> matcher) {
        beanPropertyMatcher.with("viewName", matcher);
        return this;
    }

    public ModelAndViewMatcher withViewName(final String value) {
        beanPropertyMatcher.with("viewName", Matchers.equalTo(value));
        return this;
    }

    @Override
    public void describeTo(final Description description) {
        beanPropertyMatcher.describeTo(description);
    }

    @Override
    protected boolean matchesSafely(final ModelAndView item) {
        return beanPropertyMatcher.matches(item);
    }

    @Override
    protected void describeMismatchSafely(final ModelAndView item, final Description description) {
        beanPropertyMatcher.describeMismatch(item, description);
    }
}

package io.github.marmer.sworhm.core;

public interface Converter<S, D> {
    D convert(S source);
}

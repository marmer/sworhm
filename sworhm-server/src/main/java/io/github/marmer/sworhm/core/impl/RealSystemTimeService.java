package io.github.marmer.sworhm.core.impl;

import io.github.marmer.sworhm.core.SystemTimeService;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
public class RealSystemTimeService implements SystemTimeService {
    @Override
    public LocalDateTime now() {
        // TODO: marmer 11.12.2018 implement} me
        return null;
    }
}

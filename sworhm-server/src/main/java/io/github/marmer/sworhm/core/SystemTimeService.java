package io.github.marmer.sworhm.core;

import java.time.LocalDateTime;

/**
 * Service to provide the current system time. Can be used to simulate a different system times.
 * <p>
 * This service is especially helpful for tests.
 */
public interface SystemTimeService {
    LocalDateTime now();
}

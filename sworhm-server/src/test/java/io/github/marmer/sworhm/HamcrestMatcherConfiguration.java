package io.github.marmer.sworhm;

import io.github.marmer.annotationprocessing.MatcherConfiguration;

@MatcherConfiguration({
        "io.github.marmer.sworhm.core.model",
        "io.github.marmer.sworhm.persistence.relational.entity",
        "org.springframework.http.ResponseEntity",
        "io.github.marmer.sworhm.rest.v1.BookingController",
})
public class HamcrestMatcherConfiguration {
}

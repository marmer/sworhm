package io.github.marmer.sworhm;

import io.github.marmer.annotationprocessing.MatcherConfiguration;

@MatcherConfiguration({
        "io.github.marmer.sworhm.core.model",
        "io.github.marmer.sworhm.persistence.relational.entity",
        "io.github.marmer.sworhm.web.ui.dto",
        "org.springframework.http.ResponseEntity",
})
public class HamcrestMatcherConfiguration {
}

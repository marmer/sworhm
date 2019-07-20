package io.github.marmer.sworhm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("inMemoryDb")
@ExtendWith(SpringExtension.class)
class SworhmServerApplicationTest {

    @Test
    void contextLoads() {
        //context is able to load
    }

}

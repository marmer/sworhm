package io.github.marmer.sworhm.testutils.springhelper;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntity;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.stream.Stream;

@Named
public class DbCleanupService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public void clearAll() throws Exception {
        Stream.of(
                BookingEntity.class,
                BookingDayEntity.class
        )
                .forEach(this::deleteAll);
        entityManager.flush();

    }

    private void deleteAll(final Class<? extends Object> anEntityClass) {
        entityManager.createQuery("delete from " + anEntityClass.getName()).executeUpdate();
    }

}
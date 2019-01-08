package io.github.marmer.sworhm.testutils.springhelper;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntity;
import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.stream.Stream;

@Service
public class DbCleanupService {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void clearAll() throws Exception {
        Stream.of(BookingDayEntity.class,
                BookingEntity.class)
                .forEachOrdered(this::deleteAll);
        entityManager.flush();

    }

    private void deleteAll(final Class<? extends Object> anEntityClass) {
        entityManager.createQuery("delete from " + anEntityClass.getName());
    }

}
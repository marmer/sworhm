package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class BookingDboRepositoryCustomImpl implements BookingDboRepositoryCustom {
    private final EntityManager entityManager;

    public BookingDboRepositoryCustomImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveOrUpdate(final BookingDbo booking) {
        entityManager.merge(booking);
    }
}

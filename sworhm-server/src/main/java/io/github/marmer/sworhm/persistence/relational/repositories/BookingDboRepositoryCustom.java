package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;

public interface BookingDboRepositoryCustom {
    void saveOrUpdate(BookingDbo booking);
}

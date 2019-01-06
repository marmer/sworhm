package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingEntityRepository extends JpaRepository<BookingEntity, String> {
    List<BookingEntity> findByDayDay(LocalDate day);
}

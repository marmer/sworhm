package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingDayEntityRepository extends JpaRepository<BookingDayEntity, String> {
    Optional<BookingDayEntity> findByDay(LocalDate day);
}

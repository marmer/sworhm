package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDayDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingDayEntityRepository extends JpaRepository<BookingDayDbo, String> {
    Optional<BookingDayDbo> findByDay(LocalDate day);
}

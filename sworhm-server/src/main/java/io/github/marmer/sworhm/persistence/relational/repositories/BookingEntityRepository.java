package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingEntityRepository extends JpaRepository<BookingDbo, String> {
    List<BookingDbo> findByDayDay(LocalDate day);
}

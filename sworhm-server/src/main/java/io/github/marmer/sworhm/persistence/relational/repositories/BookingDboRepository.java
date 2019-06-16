package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface BookingDboRepository extends JpaRepository<BookingDbo, String> {
    Stream<BookingDbo> findAllByDay(LocalDate day);
}

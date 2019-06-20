package io.github.marmer.sworhm.persistence.relational.repositories;

import io.github.marmer.sworhm.persistence.relational.entity.BookingDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface BookingDboRepository extends BookingDboRepositoryCustom, JpaRepository<BookingDbo, String> {
    List<BookingDbo> findAllByDay(LocalDate day);

    @Query("delete from BookingDbo b where b.id =:id")
    @Modifying
    @Transactional
    void deleteBookingById(String id);
}

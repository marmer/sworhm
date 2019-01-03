package io.github.marmer.sworhm.core.persistence.entity;

import lombok.Builder;
import lombok.Value;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Value
@Builder
@Entity
@Table(name = "BOOKING_DAY")
public class BookingDayEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private LocalDate day;
}

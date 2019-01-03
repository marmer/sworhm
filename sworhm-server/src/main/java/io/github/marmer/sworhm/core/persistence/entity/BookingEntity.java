package io.github.marmer.sworhm.core.persistence.entity;

import lombok.Builder;
import lombok.Value;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

@Value
@Builder
@Entity
@Table(name = "BOOKING")
public class BookingEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private final LocalTime startTime;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final BookingDayEntity day;
}

package io.github.marmer.sworhm.persistence.relational.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOKING")
public class BookingEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private BookingDayEntity day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int durationInMinutes;
    private String notes;
    private String ticket;
    private String description;
}

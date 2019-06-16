package io.github.marmer.sworhm.persistence.relational.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOKING", indexes = @Index(columnList = "day"))
public class BookingDbo {
    @Id
    private String id;
    private LocalDate day;
    private LocalTime startTime;
    private int durationInMinutes;
    private String notes;
    private String ticket;
    private String description;
}

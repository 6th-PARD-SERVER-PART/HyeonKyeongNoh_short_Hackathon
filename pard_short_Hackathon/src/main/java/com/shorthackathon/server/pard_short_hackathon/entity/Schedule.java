package com.shorthackathon.server.pard_short_hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScheduleId.class)
public class Schedule {
    @Id
    @Column(nullable = false)
    private String userName;

    @Id
    @Column(nullable = false)
    private LocalDateTime time;
}


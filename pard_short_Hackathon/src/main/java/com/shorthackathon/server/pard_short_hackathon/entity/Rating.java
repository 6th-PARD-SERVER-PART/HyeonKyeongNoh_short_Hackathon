package com.shorthackathon.server.pard_short_hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Rating {
    @Id
    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String targetName;

    @Column(nullable = false)
    private float rate;

    @Column(nullable = false)
    private LocalDate whenPut;
}


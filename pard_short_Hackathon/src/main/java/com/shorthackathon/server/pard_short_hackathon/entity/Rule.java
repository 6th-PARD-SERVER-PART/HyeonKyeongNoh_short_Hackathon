package com.shorthackathon.server.pard_short_hackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruleId;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private Boolean available;

    public void update(boolean b) {
        this.available = b;
    }
}


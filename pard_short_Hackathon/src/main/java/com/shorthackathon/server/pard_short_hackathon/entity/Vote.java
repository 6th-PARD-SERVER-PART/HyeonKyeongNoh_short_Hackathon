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

public class Vote {
    @Id
    @Column(nullable = false, unique = true)
    private Long ruleId;

    @Column(nullable = false)
    private Integer trueCheck;

    @Column(nullable = false)
    private Integer falseCheck;

    @Column(nullable = false)
    private Boolean available;

    public void updateTrue() {
        this.trueCheck++;
    }

    public void updateFalse() {
        this.falseCheck++;
    }

}


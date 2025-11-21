package com.shorthackathon.server.pard_short_hackathon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LogVote {
    @Id
    @Column(nullable = false, unique = true)
    private Long ruleId;

    @Column(nullable = false)
    private String userName;

}


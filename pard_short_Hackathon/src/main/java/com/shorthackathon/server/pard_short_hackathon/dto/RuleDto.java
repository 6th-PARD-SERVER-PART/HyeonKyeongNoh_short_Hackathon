package com.shorthackathon.server.pard_short_hackathon.dto;

import lombok.*;

import java.time.LocalDateTime;

public class RuleDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class PostInfo{
        private String memo;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ResInfo{
        private Long ruleId;
        private String memo;
        private Boolean available;
    }
}

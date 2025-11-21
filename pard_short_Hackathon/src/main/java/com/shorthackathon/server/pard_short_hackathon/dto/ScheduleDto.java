package com.shorthackathon.server.pard_short_hackathon.dto;

import lombok.*;

import java.time.LocalDateTime;

public class ScheduleDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ScheduleInfo{
        private String userName;
        private LocalDateTime time;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ScheduleTimeInfo{
        private LocalDateTime time;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class MemberInfo{
        private String userName;
    }
}

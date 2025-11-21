package com.shorthackathon.server.pard_short_hackathon.dto;

import lombok.*;

public class RatingDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class RatingReq{
        private String userName;
        private String targetName;
        private float rate;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class RatingRes{
        private String userName;
        private float rate;
    }
}

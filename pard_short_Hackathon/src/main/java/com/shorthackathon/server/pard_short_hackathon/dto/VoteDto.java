package com.shorthackathon.server.pard_short_hackathon.dto;

import lombok.*;

public class VoteDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class VoteResInfo{
        private Long ruleId;
        private Integer trueCheck;
        private Integer falseCheck;
        private Boolean available;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class VoteReqInfo{
        private Long ruleId;
        private String userName;
        private Boolean isCheck;
    }
}

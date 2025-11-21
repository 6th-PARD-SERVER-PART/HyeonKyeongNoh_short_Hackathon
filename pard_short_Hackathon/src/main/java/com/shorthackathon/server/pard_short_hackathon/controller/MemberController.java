package com.shorthackathon.server.pard_short_hackathon.controller;


import com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto;
import com.shorthackathon.server.pard_short_hackathon.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final ScheduleService scheduleService;

    @GetMapping("")
    public ResponseEntity<?> getMember()
    {
        try {
            List<ScheduleDto.MemberInfo> userName = scheduleService.getMember();
            return ResponseEntity.ok(userName);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
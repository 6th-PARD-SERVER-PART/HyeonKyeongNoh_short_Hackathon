package com.shorthackathon.server.pard_short_hackathon.controller;

import com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto;
import com.shorthackathon.server.pard_short_hackathon.repo.ScheduleRepo;
import com.shorthackathon.server.pard_short_hackathon.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// Test PR by mingyu
@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody List<ScheduleDto.ScheduleInfo> scheduleInfo) {
        try {
            scheduleService.save(scheduleInfo);
            return ResponseEntity.ok(200);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/{userName}")
    public ResponseEntity<?> updateSchedule(@PathVariable String userName,@RequestBody List<ScheduleDto.ScheduleTimeInfo> scheduleInfo) {
        try {
            scheduleService.updateSchedule(userName,scheduleInfo);
            return ResponseEntity.ok(200);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    //전체출력
    @GetMapping("")
    public ResponseEntity<?> getScheduleInfo() {
        try {
            List<ScheduleDto.ScheduleInfo> scheduleInfo = scheduleService.getScheduleAll();
            return ResponseEntity.ok(scheduleInfo);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    //일부출력
    @GetMapping("/{userName}")
    public ResponseEntity<?> getScheduleInfoByuserName(@PathVariable String userName) {
        try {
            List<ScheduleDto.ScheduleTimeInfo> scheduleTimeInfo = scheduleService.getScheduleByUserName(userName);
            return ResponseEntity.ok(scheduleTimeInfo);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

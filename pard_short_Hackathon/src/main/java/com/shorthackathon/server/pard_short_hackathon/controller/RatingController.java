package com.shorthackathon.server.pard_short_hackathon.controller;

import com.shorthackathon.server.pard_short_hackathon.dto.RatingDto;
import com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto;
import com.shorthackathon.server.pard_short_hackathon.service.RatingService;
import com.shorthackathon.server.pard_short_hackathon.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody RatingDto.RatingReq req) {
        try {
            ratingService.save(req);
            return ResponseEntity.ok(200);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> getUserRating(@RequestParam String userName, String targetName) {
        try {
            Integer check = ratingService.getRatingMonth(userName, targetName);
            if(check==1)
                return ResponseEntity.ok(301);
            return ResponseEntity.ok(200);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserRating(@PathVariable String userName) {
        try {
            float check = ratingService.getRatingUser(userName);
            return ResponseEntity.ok(check);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

package com.shorthackathon.server.pard_short_hackathon.controller;


import com.shorthackathon.server.pard_short_hackathon.dto.RuleDto;
import com.shorthackathon.server.pard_short_hackathon.dto.VoteDto;
import com.shorthackathon.server.pard_short_hackathon.entity.Rule;
import com.shorthackathon.server.pard_short_hackathon.entity.Vote;
import com.shorthackathon.server.pard_short_hackathon.service.RuleService;
import com.shorthackathon.server.pard_short_hackathon.service.ScheduleService;
import com.shorthackathon.server.pard_short_hackathon.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RuleService ruleService;
    private final VoteService voteService;

    @PostMapping("")
    public ResponseEntity<?> saveRule(@RequestBody RuleDto.PostInfo ruleDto)
    {
        try {
            Long id = ruleService.save(ruleDto);
            Vote res = voteService.save(id,false);
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getRule()
    {
        try {
            List<Rule> ruleList = ruleService.getRule();
            return ResponseEntity.ok(ruleList);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{ruleId}")
    public ResponseEntity<?> deleteRule(@PathVariable Long ruleId)
    {
        try {
            Vote res = voteService.save(ruleId,true);
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
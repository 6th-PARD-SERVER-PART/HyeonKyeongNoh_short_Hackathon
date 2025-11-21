package com.shorthackathon.server.pard_short_hackathon.controller;

import com.shorthackathon.server.pard_short_hackathon.dto.RuleDto;
import com.shorthackathon.server.pard_short_hackathon.dto.VoteDto;
import com.shorthackathon.server.pard_short_hackathon.entity.Vote;
import com.shorthackathon.server.pard_short_hackathon.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;
    @PostMapping("/check")
    public ResponseEntity<?> saveVote(@RequestBody VoteDto.VoteReqInfo voteInfo)
    {
        try {
            voteService.saveLogVote(voteInfo);
            return ResponseEntity.ok(200);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("")
    public ResponseEntity<?> checkVote(@RequestParam String userName, Long ruleId)
    {
        try {
            Integer check = voteService.checkLogVote(userName,ruleId);
            if(check==0)
                return ResponseEntity.ok(200);
            else
                return ResponseEntity.ok(301);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getVote()
    {
        try {
            List<Vote> vote = voteService.getLogVote();
            return ResponseEntity.ok(vote);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}

package com.shorthackathon.server.pard_short_hackathon.service;

import com.shorthackathon.server.pard_short_hackathon.dto.VoteDto;
import com.shorthackathon.server.pard_short_hackathon.entity.LogVote;
import com.shorthackathon.server.pard_short_hackathon.entity.Rule;
import com.shorthackathon.server.pard_short_hackathon.entity.Vote;
import com.shorthackathon.server.pard_short_hackathon.repo.RuleRepo;
import com.shorthackathon.server.pard_short_hackathon.repo.VoteRepo;
import com.shorthackathon.server.pard_short_hackathon.repo.LogVoteRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class VoteService {
    private final VoteRepo voteRepo;
    private final LogVoteRepo logVoteRepo;
    private final RuleRepo ruleRepo;
    private final RuleService ruleService;
    private final ScheduleService scheduleService;

    public VoteService(VoteRepo voteRepo, LogVoteRepo logVoteRepo, RuleRepo ruleRepo, RuleService ruleService, ScheduleService scheduleService) {
        this.voteRepo = voteRepo;
        this.logVoteRepo = logVoteRepo;
        this.ruleRepo = ruleRepo;
        this.ruleService = ruleService;
        this.scheduleService = scheduleService;
    }

    public Vote save(Long id, Boolean check) {
        Vote vote = Vote.builder()
                .ruleId(id)
                .trueCheck(0)
                .falseCheck(0)
                .available(check)
                .build();
        voteRepo.save(vote);
        return vote;
    }

    @Transactional
    public void saveLogVote(VoteDto.VoteReqInfo voteInfo) {
        LogVote logVote = LogVote.builder()
                .ruleId(voteInfo.getRuleId())
                .userName(voteInfo.getUserName())
                .build();
        logVoteRepo.save(logVote);
        Vote vote = voteRepo.findByRuleId(voteInfo.getRuleId());
        //몇 명인지
        Integer member = scheduleService.getMember().size()/2;


        if(voteInfo.getIsCheck())
            vote.updateTrue();
        else
            vote.updateFalse();


        //찬성이 과반수 이상이면?
        if(vote.getTrueCheck() > member){
            //delete 룰
            if(vote.getAvailable()){
                ruleService.delete(voteInfo.getRuleId());
                logVoteRepo.deleteAllByRuleId(voteInfo.getRuleId());
                voteRepo.delete(vote);
            }
            //post 룰
            else{
                Rule rule = ruleRepo.findByRuleId(voteInfo.getRuleId());
                rule.update(true);
                logVoteRepo.deleteAllByRuleId(voteInfo.getRuleId());
                voteRepo.delete(vote);
            }
        }
        //반대가 과반수 이상이면?
        else if(vote.getFalseCheck() > member){
            //delete 룰
            if(vote.getAvailable()){
                logVoteRepo.deleteAllByRuleId(voteInfo.getRuleId());
                voteRepo.delete(vote);
            }
            //post 룰
            else{
                ruleService.delete(voteInfo.getRuleId());
                logVoteRepo.deleteAllByRuleId(voteInfo.getRuleId());
                voteRepo.delete(vote);
            }
        }
    }

    public Integer checkLogVote(String userName, Long ruleId) {
        if(logVoteRepo.findByUserNameAndRuleId(userName, ruleId))
            return 1;
        else
            return 0;
    }

    public List<Vote> getLogVote() {
        return voteRepo.findAll();
    }
}

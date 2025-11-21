package com.shorthackathon.server.pard_short_hackathon.repo;

import com.shorthackathon.server.pard_short_hackathon.entity.LogVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogVoteRepo extends JpaRepository<LogVote, Integer> {
    void deleteAllByRuleId(Long  ruleId);

    LogVote findByUserNameAndRuleId(String userName, Long ruleId);
}

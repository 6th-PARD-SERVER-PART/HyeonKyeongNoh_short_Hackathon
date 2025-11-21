package com.shorthackathon.server.pard_short_hackathon.repo;

import com.shorthackathon.server.pard_short_hackathon.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Vote, Integer> {
    Vote findByRuleId(Long ruleId);
}

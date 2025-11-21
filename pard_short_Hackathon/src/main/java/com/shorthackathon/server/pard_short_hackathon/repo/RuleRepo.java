package com.shorthackathon.server.pard_short_hackathon.repo;

import com.shorthackathon.server.pard_short_hackathon.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepo extends JpaRepository<Rule, Integer> {
    void deleteByRuleId(Long ruleId);

    Rule findByRuleId(Long ruleId);
}

package com.shorthackathon.server.pard_short_hackathon.service;

import com.shorthackathon.server.pard_short_hackathon.dto.RuleDto;
import com.shorthackathon.server.pard_short_hackathon.entity.Rule;
import com.shorthackathon.server.pard_short_hackathon.repo.RuleRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class RuleService {
    private final RuleRepo ruleRepo;

    public RuleService(RuleRepo ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    public List<Rule> getRule() {
        return ruleRepo.findAll();
    }

    public Long save(RuleDto.PostInfo ruleDto) {
        Rule rule = Rule.builder()
                .memo(ruleDto.getMemo())
                .available(false)
                .build();
        ruleRepo.save(rule);
        return rule.getRuleId();
    }

    public void delete(Long ruleId) {
        ruleRepo.deleteByRuleId(ruleId);
    }
}

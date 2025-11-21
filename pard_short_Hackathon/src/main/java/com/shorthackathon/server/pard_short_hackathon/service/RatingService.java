package com.shorthackathon.server.pard_short_hackathon.service;

import com.shorthackathon.server.pard_short_hackathon.dto.RatingDto;
import com.shorthackathon.server.pard_short_hackathon.entity.Rating;
import com.shorthackathon.server.pard_short_hackathon.repo.RatingRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Getter
@Setter
public class RatingService {
    private final RatingRepo ratingRepo;

    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public void save(RatingDto.RatingReq req) {
        Rating rating = Rating.builder()
                .userName(req.getUserName())
                .targetName(req.getTargetName())
                .rate(req.getRate())
                .whenPut(LocalDate.now())
                .build();
        ratingRepo.save(rating);
    }

    public Integer getRatingMonth(String userName, String targetName) {
        LocalDate date = ratingRepo.findByUserNameAndTargetName(userName,targetName);
        if(date.plusMonths(1).isBefore(LocalDate.now()))
            return 0;
        else
            return 1;
    }

    public float getRatingUser(String userName) {
        return ratingRepo.AvgRatingByTargetName(userName);
    }
}

package com.shorthackathon.server.pard_short_hackathon.repo;

import com.shorthackathon.server.pard_short_hackathon.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RatingRepo extends JpaRepository<Rating, Integer> {
    LocalDate findByUserNameAndTargetName(String userName, String targetName);

    @Query("SELECT AVG(r.rate) " +
            "FROM Rating r WHERE r.userName= :userName GROUP BY r.userName ")
    float AvgRatingByUserName(String userName);
}

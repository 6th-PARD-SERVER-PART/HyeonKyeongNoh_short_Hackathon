package com.shorthackathon.server.pard_short_hackathon.repo;

import com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto;
import com.shorthackathon.server.pard_short_hackathon.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Integer> {
    @Query("SELECT new com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto$ScheduleInfo(s.userName, s.time)" +
            "FROM Schedule s")
    List<ScheduleDto.ScheduleInfo> findAllSchedule();

    @Query("SELECT new com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto$ScheduleTimeInfo(s.time)" +
            "FROM Schedule s " +
            "WHERE s.userName = :userName")
    List<ScheduleDto.ScheduleTimeInfo> findScheduleByUserName(String userName);

    @Query("SELECT new com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto$MemberInfo(s.userName) " +
            "FROM Schedule s GROUP BY s.userName")
    List<ScheduleDto.MemberInfo> findAllUserName();

    Schedule findScheduleByUserNameAndTime(String userName, LocalDateTime time);
}

package com.shorthackathon.server.pard_short_hackathon.service;

import com.shorthackathon.server.pard_short_hackathon.dto.ScheduleDto;
import com.shorthackathon.server.pard_short_hackathon.entity.Schedule;
import com.shorthackathon.server.pard_short_hackathon.repo.ScheduleRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public void save(List<ScheduleDto.ScheduleInfo> scheduleInfo_list) {
        for (ScheduleDto.ScheduleInfo scheduleInfo : scheduleInfo_list) {
            Schedule schedule = Schedule.builder()
                    .userName(scheduleInfo.getUserName())
                    .time(scheduleInfo.getTime())
                    .build();

            scheduleRepo.save(schedule);
        }
    }

    @Transactional
    public void updateSchedule(String userName, List<ScheduleDto.ScheduleTimeInfo> scheduleInfo_list) {
        for (ScheduleDto.ScheduleTimeInfo scheduleInfo : scheduleInfo_list){
            Schedule schedule = scheduleRepo.findScheduleByUserNameAndTime(userName, scheduleInfo.getTime());
            if(schedule!=null){
                scheduleRepo.delete(schedule);
            }
            else{
                Schedule newSchedule = Schedule.builder()
                        .userName(userName)
                        .time(scheduleInfo.getTime())
                        .build();
                scheduleRepo.save(newSchedule);
            }
        }
    }

    public List<ScheduleDto.ScheduleInfo> getScheduleAll() {
        List<ScheduleDto.ScheduleInfo> scheduleInfoList = scheduleRepo.findAllSchedule();
        return scheduleInfoList;
    }

    public List<ScheduleDto.ScheduleTimeInfo> getScheduleByUserName(String userName) {
        List<ScheduleDto.ScheduleTimeInfo> scheduleInfoList = scheduleRepo.findScheduleByUserName(userName);
        return scheduleInfoList;
    }

    public List<ScheduleDto.MemberInfo> getMember() {
        return scheduleRepo.findAllUserName();
    }
}

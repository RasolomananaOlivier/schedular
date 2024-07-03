package com.project.l3.schedular.repository.meeting;

import com.project.l3.schedular.model.Meeting;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MeetingRepository {
    List<Meeting> getMeetings();
    Meeting getMeeting(int id);
    Meeting createMeeting(Meeting meeting,  int employId, int roomId);
    Meeting updateMeeting(Meeting meeting,  int employId, int roomId);
    void deleteMeeting(int id);
}

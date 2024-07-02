package com.project.l3.schedular.repository.meetingRoom;

import com.project.l3.schedular.model.MeetingRoom;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface MeetingRoomRepository {
    List<MeetingRoom> getMeetingRooms();
    MeetingRoom getMeetingRoom(int id);
    MeetingRoom createMeetingRoom(MeetingRoom meetingRoom);
    MeetingRoom updateMeetingRoom(MeetingRoom meetingRoom);
    void deleteMeetingRoom(int id);
}

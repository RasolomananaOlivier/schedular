package com.project.l3.schedular.repository.attendance;

import com.project.l3.schedular.model.Attendance;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AttendanceRepository {
    List<Attendance> getAttendances();
    Attendance getAttendance(int id);
    Attendance createAttendance(Attendance attendance);
    Attendance updateAttendance(Attendance attendance);
    void deleteAttendance(int id);
}

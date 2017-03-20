/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Model;

import attendance.BE.Checkin;
import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.BE.Class;
import attendance.BLL.AttendanceDetailsHandler;
import attendance.BLL.CheckinHandler;
import attendance.BLL.DataHandler;
import attendance.BLL.LoginHandler;
import attendance.BLL.ScheduleHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kristof
 */
public class AttendanceModel {
    
    private DataHandler dataHandler = new DataHandler();
    private LoginHandler loginHandler = new LoginHandler();
    private CheckinHandler checkinHandler = new CheckinHandler();
    private ScheduleHandler scheduleHandler = new ScheduleHandler();
    private AttendanceDetailsHandler attHan = new AttendanceDetailsHandler();
    

    public ArrayList<Checkin> getCheckins() {
        return checkinHandler.getCheckins();
    }
    
    public List<Student> getStudents()
    {
        return dataHandler.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return dataHandler.getTeachers();
    }
    
    public int checkLogin(String uname, String pass) {
        return loginHandler.checkLogin(uname, pass);
    }
    
    public void doCheckin(CurrentStudent student, Schedule course) {
        checkinHandler.doCheckin(student,course);
    }
    
    public ArrayList<Integer> getCheckedInSchedules() {
        return checkinHandler.getCheckedInSchedules();
    }
    
    public int getMissedTotal() {
        return attHan.getMissed().size();
    }
    
    public ArrayList<Schedule> getTodaysSchedules() {
        return scheduleHandler.getTodaysSchedules();
    }
    
    public ArrayList<Schedule> getMissed() {
        return attHan.getMissed();
    }
    
    public ArrayList<Schedule> getTeacherSchedule()
    {
        return scheduleHandler.getTeacherSchedule();
    }
    
    public List<Class> getClasses()
    {
        return dataHandler.getClasses();
    }
    
    public List<Student> getStudentsByClass(int classId)
    {
        return dataHandler.getStudentsByClass(classId);
    }
    
    public String getTotalAttPercent() {
        return attHan.getTotalAttPercent();
    }
    public ArrayList<Schedule> getAllCheckedIn() 
    {
        return attHan.getAllCheckedIn();
    }
}

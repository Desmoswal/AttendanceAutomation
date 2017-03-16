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
import attendance.BLL.AttendanceDetailsHandler;
import attendance.BLL.CheckinHandler;
import attendance.BLL.DataManager;
import attendance.BLL.LoginHandler;
import attendance.BLL.ScheduleManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kristof
 */
public class AttendanceModel {
    
    private DataManager dataManager = new DataManager();
    private LoginHandler loginHandler = new LoginHandler();
    private CheckinHandler checkinHandler = new CheckinHandler();
    private AttendanceDetailsHandler attHan = new AttendanceDetailsHandler();
    private ScheduleManager schedMan = new ScheduleManager();

    public ArrayList<Checkin> getCheckins() {
        return checkinHandler.getCheckins();
    }
    
    public List<Student> getStudents()
    {
        return dataManager.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return dataManager.getTeachers();
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
        return attHan.calculateMissedTotal();
    }
    
    public ArrayList<Schedule> getTodaysSchedules() {
        return schedMan.getTodaysSchedules();
    }
    
    public ArrayList<Schedule> getMissed() {
        return attHan.getMissed();
    }
}

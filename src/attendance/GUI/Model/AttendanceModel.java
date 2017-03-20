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
import attendance.BE.Subject;
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
    

    public ArrayList<Checkin> getCheckins() {
        return checkinHandler.getCheckins();
    }
    
    public void doCheckin(CurrentStudent student, Schedule course) {
        checkinHandler.doCheckin(student,course);
    }
    
    public List<Student> getStudents()
    {
        return dataHandler.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return dataHandler.getTeachers();
    }
    
    public List<Class> getClasses()
    {
        return dataHandler.getClasses();
    }
    
    public List<Student> getStudentsByClass(int classId)
    {
        return dataHandler.getStudentsByClass(classId);
    }
    
    public List<Subject> getSubjectsForStudent(int studentid) {
        return dataHandler.getSubjectsForStudent(studentid);
    }
    
    public int checkLogin(String uname, String pass) {
        return loginHandler.checkLogin(uname, pass);
    }
    
    public ArrayList<Schedule> getAllCheckedinForStudent(int studentid, int classid) {
        return scheduleHandler.getAllCheckedinForStudent(studentid, classid);
    }
    
    /*public int getMissedSchedulesNumberForStudent(int studentid, int classid) {
        return scheduleHandler.getMissedSchedulesForStudent(studentid, classid).size();
    }*/
    
    public ArrayList<Schedule> getTodaysSchedulesForStudent(int studentid,int classid) {
        return scheduleHandler.getTodaysSchedulesForStudent(studentid,classid);
    }
    
    public ArrayList<Schedule> getMissedSchedulesForStudent(int studentid, int classid) {
        return scheduleHandler.getMissedSchedulesForStudent(studentid, classid);
    }
    
    public ArrayList<Schedule> getSchedulesForTeacher(int teacherid)
    {
        return scheduleHandler.getSchedulesForTeacher(teacherid);
    }
    
    public String getTotalAttPercentForStudent(int studentid, int classid) {
        return scheduleHandler.getTotalAttPercentForStudent(studentid, classid);
    }

    public ArrayList<Schedule> getSchedulesForClass(int classid) {
        return scheduleHandler.getSchedulesForClass(classid);
    }
    
    public ArrayList<Schedule> getAllSchedulesForStudent(int studentid, int classid) {
        return scheduleHandler.getAllSchedulesForStudent(studentid, classid);
    }
}

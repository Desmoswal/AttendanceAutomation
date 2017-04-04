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
import attendance.BLL.SearchHandler;
import attendance.BLL.SearchHandler.SearchType;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kristof
 */
public class AttendanceModel {
    
    private DataHandler dataHandler = new DataHandler();
    private LoginHandler loginHandler = new LoginHandler();
    private SearchHandler searchHandler = new SearchHandler();
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
    
    public String getMostMissedDayOfWeekForStudent(int studentid, int classid) {
        return dataHandler.calculateMostMissedDayOfWeekForStudent(studentid, classid);
    }
    
    public String getAvgAttForClass(int classid) {
        return dataHandler.calculateAvgAttForClass(classid);
    }
    
    public String getMostMissedCourseForClass(int classid) {
        return dataHandler.calculateMostMissedCourseForClass(classid);
    }
    
    public String getMostAttCourseForClass(int classid) {
        return dataHandler.calculateMostAttCourseForClass(classid);
    }
    
    public Student getMostMissedStudentForClass(int classid) {
        return dataHandler.calculateMostMissedStudentForClass(classid);
    }
    
    public String getMostMissedStudentsMostMissedCourse(int classid) {
        return dataHandler.calculateMostMissedStudentsMostMissedCourse(classid);
    }
    
    public Student getMostAttStudentForClass(int classid) {
        return dataHandler.calculateMostAttStudentForClass(classid);
    }
    
    public String getMostAttStudentMostAttCourse(int classid) {
        return dataHandler.calculateMostAttStudentsMostAttCourse(classid);
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
    
    public ArrayList<Schedule> getAllSchedulesForTeacher(int teacherid)
    {
        return scheduleHandler.getAllSchedulesForTeacher(teacherid);
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
    
    public ArrayList<Schedule> getSubjectCheckinForStudent(int studentid, int classid, int subjectid) {
        return scheduleHandler.getSubjectCheckinForStudent(studentid, classid, subjectid);
    }
    
    public ArrayList<Schedule> getSubjectMissedForStudent(int studentid, int classid, int subject) {
        return scheduleHandler.getSubjectMissedForStudent(studentid, classid, subject);
    }
    
    public void deleteCheckin(Student student, Schedule schedule)
    {
        checkinHandler.deleteCheckin(student, schedule);
    }
    
    public void adminCheckin(Student student, Schedule schedule)
    {
        checkinHandler.adminCheckin(student, schedule);
    }
    
    public void setOnline(CurrentStudent student, int state)
    {
        loginHandler.setOnline(student, state);
    }
    
    public ArrayList<Student> getOnlineStudents()
    {
        return loginHandler.getOnlineStudents();
    }
    
    public String getTotalAttPercentForSubject(int studentid, int classid, int subjectid)
    {
        return scheduleHandler.getTotalAttPercentForSubject(studentid, classid, subjectid);
    }
    
    public void addSchedule(String startTime, String endTime, int subject, int classId, String room, int teacher)
    {
        scheduleHandler.addSchedule(startTime, endTime, subject, classId, room, teacher);
    }
    
    public void updateSchedule(int id, String startTime, String endTime, int subject, int classId, String room, int teacher, int canceled)
    {
        scheduleHandler.updateSchedule(id, startTime, endTime, subject, classId, room, teacher, canceled);
    }
    
    public void deleteSchedule(int scheduleId)
    {
        scheduleHandler.deleteSchedule(scheduleId);
    }
    
    public ArrayList<Subject> getAllSubjects()
    {
        return dataHandler.getAllSubjects();
    }
    
    public <T> List<T> doSearch(String word, List<T> inWhat, SearchType type) {
        return searchHandler.search(word, inWhat, type);
    }
}

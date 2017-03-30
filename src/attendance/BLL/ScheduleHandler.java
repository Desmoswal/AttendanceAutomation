/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.DAL.DAOSchedule;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desmoswal
 */
public class ScheduleHandler
{
    private DAOSchedule daoSchedule = new DAOSchedule();
    
    public ArrayList<Schedule> getSchedulesForClass(int classid) {
        return daoSchedule.getSchedulesForClass(classid);
    }
    
    public ArrayList<Schedule> getTodaysSchedulesForStudent(int studentid, int classid) {
        return daoSchedule.getTodaysSchedulesForStudent(studentid,classid);
    }
    
    public ArrayList<Schedule> getSchedulesForTeacher(int teacherid)
    {
        return daoSchedule.getSchedulesForTeacher(teacherid);
    }
    
    public ArrayList<Schedule> getAllSchedulesForTeacher(int teacherid)
    {
        return daoSchedule.getAllSchedulesForTeacher(teacherid);
    }
    
    public ArrayList<Schedule> getMissedSchedulesForStudent(int studentid,int classid) {
        return daoSchedule.getMissedSchedulesForStudent(studentid, classid);
    }
    
    public ArrayList<Schedule> getAllSchedulesForStudent(int studentid, int classid) {
        return daoSchedule.getAllSchedulesForStudent(studentid, classid);
    }
    
    public ArrayList<Schedule> getAllCheckedinForStudent(int studentid, int classid) {
        return daoSchedule.getAllCheckedinForStudent(studentid, classid);
    }
    
    public String getTotalAttPercentForStudent(int studentid, int classid) {
        DecimalFormat numberFormat = new DecimalFormat("#.#");
        int total =  this.getMissedSchedulesForStudent(studentid, classid).size() + this.getAllCheckedinForStudent(studentid, classid).size();
        return numberFormat.format(
                ((float)this.getAllCheckedinForStudent(studentid,classid).size() / (float)total) *100
        );
    }
    
    public ArrayList<Schedule> getSubjectCheckinForStudent(int studentid, int classid, int subjectid) {
        return daoSchedule.getSubjectCheckinForStudent(studentid, classid, subjectid);
    }
    
    public ArrayList<Schedule> getSubjectMissedForStudent(int studentid, int classid, int subjectid) {
        return daoSchedule.getSubjectMissedForStudent(studentid, classid, subjectid);
    }
    
    public String getTotalAttPercentForSubject(int studentid, int classid, int subjectid) {
        DecimalFormat numberFormat = new DecimalFormat("#.#");
        int total =  this.getSubjectMissedForStudent(studentid, classid, subjectid).size() + this.getSubjectCheckinForStudent(studentid, classid, subjectid).size();
        return numberFormat.format(
                ((float)this.getSubjectCheckinForStudent(studentid, classid, subjectid).size() / (float)total) *100
        );
    }
}

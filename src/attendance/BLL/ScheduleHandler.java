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
        return numberFormat.format(
                ((float)daoSchedule.getAllCheckedinForStudent(studentid,classid).size() / (float)daoSchedule.getAllSchedulesForStudent(studentid, classid).size()) *100
        );
    }
}

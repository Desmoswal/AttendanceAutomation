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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Desmoswal
 */
public class ScheduleHandler
{
    private DAOSchedule daoSchedule = new DAOSchedule();
    
    
    public ArrayList<Schedule> getSchedules()
    {
        return daoSchedule.getSchedules();
    }
    
    public ArrayList<Schedule> getTodaysSchedules() {
        if(daoSchedule.getTodaysSchedules() != null) {
            return daoSchedule.getTodaysSchedules();
        } else {
            System.out.println("Today's schedules list is null! Something is wrong with the database!");
            return null;
        }
    }
    public ArrayList<Schedule> getTeacherSchedule()
    {
        return daoSchedule.getTeacherSchedules();
    }
    
}

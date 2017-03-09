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
public class ScheduleManager
{
    private DAOSchedule daoSchedule = new DAOSchedule();
    
    
    public ArrayList<String> getSchedules()
    {
        return daoSchedule.getSchedules();
    }
    
    
}

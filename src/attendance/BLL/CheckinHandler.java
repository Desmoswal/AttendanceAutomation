/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.Checkin;
import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.BE.User;
import attendance.DAL.CheckinManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kristof
 */
public class CheckinHandler {
    private CheckinManager chkMan = new CheckinManager();
    private ScheduleHandler sm = new ScheduleHandler();
    
    /*public void doCheckin(User user,Schedule sched) throws SQLException {
        Connection con = SQLConnector.getConnection("10.176.111.31","CS2016B_16","CS2016B_16","CS2016B_16_Attendance");
        
        Statement s = con.createStatement();
        s.execute("insert into CheckIn (SchedId,StudId) values ("+sched.getId()+","+user.getId()+");");
    }*/
    
    public ArrayList<Checkin> getCheckins() {
        return chkMan.getCheckins();
    }
    
    public void unCheck(User user) {
        
    }
    
    public void doCheckin(CurrentStudent student,Schedule course) {
        chkMan.insertCheckin(student, course);
    }
    
    public ArrayList<Integer> getCheckedInSchedules() {
        ArrayList<Integer> checkedin = new ArrayList<>();
        for (Checkin checkin : this.getCheckins()) {
            for (Schedule schedule : sm.getSchedules()) {
                if(CurrentStudent.getInstance().getId() == checkin.getStudentId()) {
                    if(checkin.getSchedId() == schedule.getId())
                        checkedin.add(schedule.getId());
                }
                
            }
        }
        
        return checkedin;
    }
}

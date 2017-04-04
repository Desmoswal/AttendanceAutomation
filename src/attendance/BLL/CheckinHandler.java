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
    private ScheduleHandler sh = new ScheduleHandler();
    
    /*public void doCheckin(User user,Schedule sched) throws SQLException {
        Connection con = SQLConnector.getConnection("10.176.111.31","CS2016B_16","CS2016B_16","CS2016B_16_Attendance");
        
        Statement s = con.createStatement();
        s.execute("insert into CheckIn (SchedId,StudId) values ("+sched.getId()+","+user.getId()+");");
    }*/
    
    public ArrayList<Checkin> getCheckins() {
        return chkMan.getCheckins();
    }
    
    public void unCheck(User user) {
        System.out.println("buildstudent runs");
    }
    
    public void doCheckin(CurrentStudent student,Schedule course) {
        chkMan.insertCheckin(student, course);
    }
    
    public void deleteCheckin(Student student, Schedule schedule)
    {
        chkMan.deleteCheckin(student, schedule);
    }
    
    public void adminCheckin(Student student, Schedule schedule)
    {
        chkMan.adminCheckin(student, schedule);
    }
}

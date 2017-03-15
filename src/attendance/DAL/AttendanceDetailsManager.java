/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.CurrentStudent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kristof
 */
public class AttendanceDetailsManager {
    private SQLConnectionManager conMan = new SQLConnectionManager();
    private CurrentStudent currentStudent = CurrentStudent.getInstance();
    
    public int getAllCheckedin() {
        
        try(Connection con = conMan.getConnection()) {
            String getCheckInSchedulesForStudent = 
                    "select [Schedule].[Id],[Schedule].[StartTime],[Schedule].[EndTime] " //get the number of rowa
                    + "from [Schedule],[Student],[Class],[Checked_In] " //needed tables
                    
                    + "where [Checked_In].[StudentId] = [Student].[Id] "
                    + "and [Schedule].[Class] = [Class].[Id] "
                    + "and [Checked_In].[SchedId] = [Schedule].[Id] " //make connections with tables
                    
                    + "and [Student].[Id] = ? and [Schedule].[Class] = ?"; //give student data
            PreparedStatement s = con.prepareStatement(getCheckInSchedulesForStudent);
            s.setInt(1, currentStudent.getId());
            s.setInt(2, currentStudent.getClassid());
            ResultSet rs = s.executeQuery();
            int rows = 0;
            Date time = new Date();
            while(rs.next()) {
                Date startDate = rs.getDate("StartTime");
                Date endDate = rs.getDate("EndTime");
                Time startTime = rs.getTime("StartTime");
                Time endTime = rs.getTime("EndTime");
                if(time.compareTo(endTime)>0 && time.compareTo(endDate)>0) {
                    rows++;
                }
            }
            con.close();
            
            return rows;

        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return -10;
    }
    
    public int getAllSchedules() {
        try(Connection con = conMan.getConnection()) {
            String getAllSchedulesForStudent = 
                    "select [Schedule].[Id],[Schedule].[StartTime],[Schedule].[EndTime] " //number of rows
                    + "from [Schedule],[Student],[Class] " //needed tables
                    + "where [Schedule].[Class] = [Class].[Id] " //connection between tables
                    + "and [Student].[Id] = ? and [Class].[Id] = ?"; //give student data
            
            PreparedStatement s = con.prepareStatement(getAllSchedulesForStudent);
            s.setInt(1, currentStudent.getId());
            s.setInt(2, currentStudent.getClassid());
            ResultSet rs = s.executeQuery();
            int rows = 0;
            Date time = new Date();
            while(rs.next()) {
                Date startDate = rs.getDate("StartTime");
                Date endDate = rs.getDate("EndTime");
                Time startTime = rs.getTime("StartTime");
                Time endTime = rs.getTime("EndTime");
                if(time.compareTo(endTime)>0 && time.compareTo(endDate)>0) {
                    rows++;
                }
            }
            
            con.close();
            
            return rows;
        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return -10;
    }
    
    
}

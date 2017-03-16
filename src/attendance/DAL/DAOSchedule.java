/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.Schedule;
import attendance.BE.CurrentStudent;
import attendance.BE.CurrentTeacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Desmoswal
 */
public class DAOSchedule
{
    SQLConnectionManager conManager;
    CurrentStudent currentStudent = CurrentStudent.getInstance();
    CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    
    public DAOSchedule()
    {
        conManager = new SQLConnectionManager();
    }
    
    public void addSchedule(int id, String startTime, String endTime, int subject, int classId, String room, int teacher)
    {
        try(Connection con = conManager.getConnection())
        {
            String sqlCommand =
            "INSERT INTO Schedule(Id, StartTime, EndTime, Subject, Class, Room, Teacher) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstat = con.prepareStatement(sqlCommand);
            pstat.setInt(1, id);
            pstat.setString(2, startTime);
            pstat.setString(3, endTime);
            pstat.setInt(4, subject);
            pstat.setInt(5, classId);
            pstat.setString(6, room);
            pstat.setInt(7, teacher);
            pstat.executeUpdate();
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
        }
    }
    
    public boolean deleteSchedule(int scheduleId)
    {
        // DELETE FROM Schedule WHERE id=
        try(Connection con = conManager.getConnection())
        {
            String sqlCommand = 
                    "DELETE FROM Schedule WHERE Id=" + scheduleId;
            Statement stmt = con.createStatement();
            return stmt.execute(sqlCommand);
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
            return false;
        }
        
    }
    
    public ArrayList<Schedule> getSchedules()
    {
        try(Connection con = conManager.getConnection())
        {
            //System.out.println(currentStudent.getClassid());
            String query =  "select Schedule.Id, Subject.Name as 'Subject', Schedule.StartTime, Schedule.EndTime, Class.Name as 'Class', Teacher.Monogram as 'Teacher', Schedule.Room from Class,Schedule,Subject,Teacher where Class.Id = Schedule.Class and Teacher.Id = Schedule.Teacher and Subject.Id = Schedule.Subject and Class.Id = 2";
            
                    
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Schedule> schedules = new ArrayList<>();
            while(rs.next())
            {
                schedules.add(new Schedule(Integer.parseInt(rs.getString("Id")), rs.getTime("StartTime"), rs.getTime("EndTime"),rs.getString("Class"), rs.getString("Subject"), rs.getString("Room"), rs.getString("Teacher")));
                
            }
            return schedules;
            
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }

    public void updateSchedule(
            int id, Time startTime, Time endTime, int subject, int classId, String room, int teacher)
    {
        try(Connection con = conManager.getConnection())
        {
            String sqlQuery =
            "UPDATE Schedule SET Id=?, StartTime=?, EndTime=?, Subject=?, Class=?, Room=?, Teacher=? WHERE id=?";
            PreparedStatement pstmt =
               con.prepareStatement(sqlQuery);
            
            pstmt.setInt(1, id);
            pstmt.setTime(2, startTime);
            pstmt.setTime(3, endTime);
            pstmt.setInt(4, subject);
            pstmt.setInt(5, classId);
            pstmt.setString(6, room);
            pstmt.setInt(7, teacher);
            
            pstmt.execute();
            
        }
        catch (SQLException sqle)
        {
            System.err.println(sqle);
        }
        
    }
    
    public ArrayList<Schedule> getTeacherSchedules()
    {
        try(Connection con = conManager.getConnection())
        {
            //System.out.println(currentStudent.getClassid());
            String query =  "SELECT Schedule.* FROM Schedule WHERE Schedule.Teacher = "+ currentTeacher.getId();
            
                    
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Schedule> schedules = new ArrayList<>();
            while(rs.next())
            {
                schedules.add(new Schedule(Integer.parseInt(rs.getString("Id")), rs.getTime("StartTime"), rs.getTime("EndTime"),rs.getString("Class"), rs.getString("Subject"), rs.getString("Room"), rs.getString("Teacher")));
                
            }
            return schedules;
            
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }
}

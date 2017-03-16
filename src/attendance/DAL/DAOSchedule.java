/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.Schedule;
import attendance.BE.CurrentStudent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desmoswal
 */
public class DAOSchedule
{
    SQLConnectionManager conManager;
    CurrentStudent currentStudent = CurrentStudent.getInstance();
    
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
                            //"SELECT [Schedule].[Id] FROM Schedule";
                            //"SELECT [Schedule].[Id], [Subject].[Name] as 'Subject', [Schedule].[StartTime], [Schedule].[EndTime], [Class].[Name] as 'Class', [Teacher].[Monogram] as 'Teacher', Schedule.Room" +
                            //"from [Class],[Schedule],[Subject],[Teacher]";// +
                            //"where [Class].[Id] = [Schedule].[Class]" +
                            //"and [Teacher].[Id] = [Schedule].[Teacher]" +
                            //"and [Subject].[Id] = [Schedule].[Subject]" +
                            //"and [Class].[Id] = 2;"; //+ currentStudent.getClassid() +";";
                    
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Schedule> schedules = new ArrayList<>();
            while(rs.next())
            {
                schedules.add(new Schedule(Integer.parseInt(rs.getString("Id")), rs.getTime("StartTime"), rs.getTime("EndTime"),rs.getString("Class"), rs.getString("Subject"), rs.getString("Room"), rs.getString("Teacher")));
                //schedules.add(new Schedule(Integer.parseInt(rs.getString("Id")), rs.getString("StartTime"), rs.getString("EndTime"), rs.getString("Subject"), rs.getString("Class"), rs.getString("Room"), rs.getString("Teacher")));//Integer.parseInt(rs.getString("Id"), Time.parse(rs.getString("StartTime"), Time.parse(rs.getString("EndTime"), Integer.parseInt("Subject"), rs.getString("Room"), Integer.parseInt("Teacher"))))));
                //String scheduleString = "";
                //scheduleString += rs.getString("Id") + ", ";
                //scheduleString +=rs.getString("StartTime") + ", ";
                //scheduleString +=rs.getString("EndTime") + ", ";
                //scheduleString +=rs.getString("Subject") + ", ";
                //scheduleString +=rs.getString("Class") + ", ";
                //scheduleString +=rs.getString("Room") + ", ";
                //scheduleString +=rs.getString("Teacher");
                //schedules.add(scheduleString);
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
    
    public ArrayList<Schedule> getTodaysSchedules() {
        String query =
                "select " //selecting columns. only these columns will be available in the result. select * would give us a mess, as it would display ALL columns from ALL the 4 tables we connected.
                + "[Schedule].[Id]," //schedule's id
                + "[Schedule].[StartTime]," //schedule's startime
                + "[Schedule].[EndTime]," //schedule's endtime
                + "[Schedule].[Class]," //class id (this is an int!!!)
                + "[Subject].[Name] as 'SubjectName'," //subject name (this is a string, not an int! see in connections!)
                + "[Schedule].[Room]," //schedule's classroom
                + "[Teacher].[Name] as 'TeacherName'," //teacher name (this is a string, not an int! see in connections!)
                + "[Class].[Name] as 'ClassName' " //class name (like CS2016B) (string! not int! see in connections!)
                
                + "from [Schedule],[Class],[Student],[Subject],[Teacher] " //needed tables
                //=====Table connections====
                + "where [Schedule].[Class] = [Class].[Id] " //connecting classes, so given class id will be in schedules. see last line, where we provide data.
                + "and [Schedule].[Teacher] = [Teacher].[Id] " //connecting teacher tables on teacher id. This will allow to display teacher's name on schedules
                + "and [Schedule].[Subject] = [Subject].[Id] " //connectig subject tables on subject id. this will allow us to display subject name on schedules.
                + "and [Schedule].[Class] = [Class].[Id] " //connecting class tables on class id. this will allow us to show class's name.
                
                + "and [Student].[Id] = ? and [Class].[Id] = ?"; //providing student's data, so it will select rows with the actual student's id and his class.
        try(Connection con = conManager.getConnection()) {
            PreparedStatement s = con.prepareStatement(query);
            s.setInt(1, currentStudent.getId());
            s.setInt(2, currentStudent.getClassid());
            ResultSet rs = s.executeQuery();
            ArrayList<Schedule> todaysScheds = new ArrayList<>();
            Date now = new Date();
            
            while(rs.next()) {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                if(endTime.after(now)) {
                    todaysScheds.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
                }
            }
            con.close();
            return todaysScheds;
        }catch(SQLException e) {
            Logger.getLogger(DAOSchedule.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}

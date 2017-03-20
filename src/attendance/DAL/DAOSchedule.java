/*
 * To change this license , choose License Headers in Project Properties.
 * To change this template file,header choose Tools | Templates
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desmoswal
 */
public class DAOSchedule extends SQLConnectionManager
{
    //SQLConnectionManager conManager;
    CurrentStudent currentStudent = CurrentStudent.getInstance();
    CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    
    public DAOSchedule()
    {
        //conManager = new SQLConnectionManager();
    }
    
    /**
     * Adds a schedule to the database.
     * @param id
     * @param startTime
     * @param endTime
     * @param subject
     * @param classId
     * @param room
     * @param teacher 
     */
    public void addSchedule(int id, String startTime, String endTime, int subject, int classId, String room, int teacher)
    {
        try(Connection con = super.getConnection())
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
    
    /**
     * Deletes a schedule from the database.
     * @param scheduleId
     * @return 
     */
    public boolean deleteSchedule(int scheduleId)
    {
        // DELETE FROM Schedule WHERE id=
        try(Connection con = super.getConnection())
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
    
    /**
     * Modified a schedule in the database.
     * @param id
     * @param startTime
     * @param endTime
     * @param subject
     * @param classId
     * @param room
     * @param teacher 
     */
    public void updateSchedule(
            int id, Time startTime, Time endTime, int subject, int classId, String room, int teacher)
    {
        try(Connection con = super.getConnection())
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
    
    /**
     * Gets all schedules for a specified class.
     * @param classid
     * @return 
     */
    public ArrayList<Schedule> getSchedulesForClass(int classid)
    {
        try(Connection con = super.getConnection())
        {
            //System.out.println(currentStudent.getClassid());
            String query =  "select "
                    + "Schedule.Id, "
                    + "Subject.Name as 'SubjectName', "
                    + "Schedule.StartTime, "
                    + "Schedule.EndTime, "
                    + "Class.Id as 'ClassId', "
                    + "Class.Name as 'ClassName', "
                    + "Teacher.Monogram as 'TeacherName', "
                    + "Schedule.Room "
                    
                    + "from Class,Schedule,Subject,Teacher "
                    
                    + "where Class.Id = Schedule.Class "
                    + "and Teacher.Id = Schedule.Teacher "
                    + "and Subject.Id = Schedule.Subject and Class.Id = "+classid;
            
                    
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Schedule> schedules = new ArrayList<>();
            while(rs.next())
            {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                schedules.add(new Schedule(rs.getInt("Id"), startTime, endTime,rs.getInt("ClassId"),rs.getString("ClassName"), rs.getString("SubjectName"), rs.getString("Room"), rs.getString("TeacheName")));
                
            }
            return schedules;
            
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }
    
    /**
     * Gets schedules only for today for a specified student.
     * @param studentid
     * @param classid
     * @return 
     */
    public ArrayList<Schedule> getTodaysSchedulesForStudent(int studentid, int classid) {
        String query =
                "select " //selecting columns. only these columns will be available in the result. select * would give us a mess, as it would display ALL columns from ALL the 4 tables we connected.
                + "[Schedule].[Id]," //schedule's id
                + "[Schedule].[StartTime]," //schedule's startime
                + "[Schedule].[EndTime]," //schedule's endtime
                + "[Schedule].[Class] as 'ClassId'," //class id (this is an int!!!)
                + "[Subject].[Name] as 'SubjectName'," //subject name (this is a string, not an int! see in connections!)
                + "[Schedule].[Room]," //schedule's classroom
                + "[Teacher].[Monogram] as 'TeacherName'," //teacher name (this is a string, not an int! see in connections!)
                + "[Class].[Name] as 'ClassName' " //class name (like CS2016B) (string! not int! see in connections!)
                
                + "from [Schedule],[Class],[Student],[Subject],[Teacher] " //needed tables
                //=====Table connections====
                + "where [Schedule].[Class] = [Class].[Id] " //connecting classes, so given class id will be in schedules. see last line, where we provide data.
                + "and [Schedule].[Teacher] = [Teacher].[Id] " //connecting teacher tables on teacher id. This will allow to display teacher's name on schedules
                + "and [Schedule].[Subject] = [Subject].[Id] " //connectig subject tables on subject id. this will allow us to display subject name on schedules.
                + "and [Schedule].[Class] = [Class].[Id] " //connecting class tables on class id. this will allow us to show class's name.
                
                + "and [Student].[Id] = "+studentid+" and [Class].[Id] = "+classid; //providing student's data, so it will select rows with the actual student's id and his class.
        try(Connection con = super.getConnection()) {
            Statement s = con.createStatement();
            
            ResultSet rs = s.executeQuery(query);
            ArrayList<Schedule> todaysScheds = new ArrayList<>();
            Date now = new Date();

            while(rs.next()) {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                if(endTime.after(now) && startTime.before(now)) { //comparing time. see details in AttendanceDetailsManager.getMissedSchedules()
                    todaysScheds.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getInt("ClassId"),rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
                }
            }
            con.close();
            return todaysScheds;
        }catch(SQLException e) {
            Logger.getLogger(DAOSchedule.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    /**
     * Gets ALL schedules for a specified teacher
     * @param teacherid
     * @return 
     */
    public ArrayList<Schedule> getSchedulesForTeacher(int teacherid)
    {
        try(Connection con = super.getConnection())
        {
            //System.out.println(currentStudent.getClassid());
            String query = 
                    "select " //selecting columns. only these columns will be available in the result. select * would give us a mess, as it would display ALL columns from ALL the 4 tables we connected.
                    + "[Schedule].[Id]," //schedule's id
                    + "[Schedule].[StartTime]," //schedule's startime
                    + "[Schedule].[EndTime]," //schedule's endtime
                    + "[Schedule].[Class] as 'ClassId'," //class id (this is an int!!!)
                    + "[Subject].[Name] as 'SubjectName'," //subject name (this is a string, not an int! see in connections!)
                    + "[Schedule].[Room]," //schedule's classroom
                    + "[Teacher].[Monogram] as 'TeacherName'," //teacher name (this is a string, not an int! see in connections!)
                    + "[Class].[Name] as 'ClassName' " //class name (like CS2016B) (string! not int! see in connections!)
                    
                    + "from [Schedule],[Class],[Student],[Subject],[Teacher] " //needed tables
                    
                    //=====Table connections====
                    + "where [Schedule].[Class] = [Class].[Id] " //connecting classes, so given class id will be in schedules. see last line, where we provide data.
                    + "and [Schedule].[Teacher] = [Teacher].[Id] " //connecting teacher tables on teacher id. This will allow to display teacher's name on schedules
                    + "and [Schedule].[Subject] = [Subject].[Id] " //connectig subject tables on subject id. this will allow us to display subject name on schedules.
                    + "and [Schedule].[Class] = [Class].[Id] " //connecting class tables on class id. this will allow us to show class's name.
                    + "and [Teacher].[Id] = "+teacherid;
                    
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Schedule> schedules = new ArrayList<>();
            while(rs.next())
            {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                schedules.add(new Schedule(rs.getInt("Id"), startTime, endTime,rs.getInt("ClassId"),rs.getString("ClassName"), rs.getString("SubjectName"), rs.getString("Room"), rs.getString("TeacherName")));
                
            }
            return schedules;
            
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }
    
    /**
     * Gets all MISSED schedules for a specified student
     * @param studentid
     * @param classid
     * @return 
     */
    public ArrayList<Schedule> getMissedSchedulesForStudent(int studentid, int classid) {
        try(Connection con = super.getConnection()) {
            String query =
                    "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
                    + "[Class].[Id] as 'ClassId',"
                    + "[Class].[Name] as 'ClassName'," //class name as string (see connections to know how we get this
                    + "[Subject].[Name] as 'SubjectName'," //subject name as string, also connections for more info
                    + "[Schedule].[Room],"
                    + "[Teacher].[Name] as 'TeacherName' " //teacher name as string. see connections

                    + "from [Schedule],[Class],[Subject],[Teacher],[Student] " //needed tables...
                    
                    /*=======Connections======*/
                    + "where [Class].[Id] = [Schedule].[Class] " //connecting Class table and Schedule table on class id. so you will have all classnames assigned by ids correctly.
                    + "and [Subject].[Id] = [Schedule].[Subject] " //connecting Subject and Schedule table on subject id. so we can get subject name too
                    + "and [Teacher].[Id] = [Schedule].[Teacher] " //connecting Teacher table to Schdule table. same thing as above.
                    + "and [Student].[Class] = [Schedule].[Class] " //connecting Schedule and Student table on CLASS ID. therefore we get all schedules assigned to a student's class. in other words, we get the lessons only for the given student's class.
                    
                    + "and [Class].[Id] = ? and [Student].[Id] = ? " //provide info. we give the student id here so the query will 'filter' results for only the given student id. giving class id is not necessary as we connected Student->Class->Schedule tables, but it's for safety reasons...
                    + "and [Schedule].[Id] not in " //here we filter the missed classes. we want to show the schedules only that are NOT IN the checked in table! so, we have to select all checkins but ONLY FOR the given student
                        + "(select [Checked_In].[SchedId] " //we need only schedule ids as we are checking it above
                        + "from [Checked_In] " //we need only Checked_In table for that.
                        + "where [Checked_In].[StudentId] = ?)"; //we want checkins for only the given student.
            //so finally we get all data for a schedule constructor. one row is one schedule. columns are displayed a we selected them in the beginning. only those schedules will display which are not in the checked in table.
                    
                    PreparedStatement s = con.prepareStatement(query);
                    s.setInt(1, classid); //we give classid for first questionmark
                    s.setInt(2, studentid); //we give studentid for second questionmark
                    s.setInt(3, studentid); //also for thrid q. mark
                    ResultSet rs = s.executeQuery(); //get data
                    ArrayList<Schedule> missed = new ArrayList<>(); //prepare to fill data
                    Date now = new Date(); //creating datetime object with time of RIGHT NOW. so we can compare schedule times with it
                    while(rs.next()) { //for every line...
                        Timestamp startTime = rs.getTimestamp("StartTime"); //create timestamp from current schedules starttime. this is similar to Date
                        Timestamp endTime = rs.getTimestamp("EndTime"); //also for endtime.
                        if(now.after(endTime)) { //only add a schedule to 'missed' if it's already ended. so if RIGHT NOW is later than endTime.
                            missed.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getInt("ClassId"),rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
                        }
                    }
                    con.close(); //finished!
                    System.out.println(missed);
                    return missed; //returning the list with only schedules that are already ended and not checked in.
        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    /**
     * do we need this?
     * @param studentId
     * @param classId
     * @return 
     */
    public int getAttendedCourses(int studentId, int classId) {
        
        try(Connection con = super.getConnection()) {
            String getCheckInSchedulesForStudent = 
                    "select [Schedule].[Id],[Schedule].[StartTime],[Schedule].[EndTime] " //get the number of rows
                    + "from [Schedule],[Student],[Class],[Checked_In] " //needed tables
                    
                    + "where [Checked_In].[StudentId] = [Student].[Id] "
                    + "and [Schedule].[Class] = [Class].[Id] "
                    + "and [Checked_In].[SchedId] = [Schedule].[Id] " //make connections with tables
                    
                    + "and [Student].[Id] = ? "; //give student data
            PreparedStatement s = con.prepareStatement(getCheckInSchedulesForStudent);
            s.setInt(1, studentId);
            //s.setInt(2, classId);
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
    
    /**
     * Gets a specified student's all schedules
     * @return 
     */
    public ArrayList<Schedule> getAllSchedulesForStudent(int studentid, int classid) {
        ArrayList<Schedule> allscheds = new ArrayList<>();
        try(Connection con = super.getConnection()) {
            String query = 
                   "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
                    + "[Class].[Id] as 'ClassId',"
                    + "[Class].[Name] as 'ClassName'," //class name as string (see connections to know how we get this
                    + "[Subject].[Name] as 'SubjectName'," //subject name as string, also connections for more info
                    + "[Schedule].[Room],"
                    + "[Teacher].[Monogram] as 'TeacherName' " //teacher name as string. see connections

                    + "from [Schedule],[Class],[Subject],[Teacher],[Student] " //needed tables...
                    
                    /*=======Connections======*/
                    + "where [Class].[Id] = [Schedule].[Class] " //connecting Class table and Schedule table on class id. so you will have all classnames assigned by ids correctly.
                    + "and [Subject].[Id] = [Schedule].[Subject] " //connecting Subject and Schedule table on subject id. so we can get subject name too
                    + "and [Teacher].[Id] = [Schedule].[Teacher] " //connecting Teacher table to Schdule table. same thing as above.
                    + "and [Student].[Class] = [Schedule].[Class] " //connecting Schedule and Student table on CLASS ID. therefore we get all schedules assigned to a student's class. in other words, we get the lessons only for the given student's class.
                    
                    + "and [Class].[Id] = "+classid+" and [Student].[Id] = "+studentid; //provide info. we give the student id here so the query will 'filter' results for only the given student id. giving class id is not necessary as we connected Student->Class->Schedule tables, but it's for safety reasons...
            
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            
            while(rs.next()) {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                    allscheds.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getInt("ClassId"),rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
            }
            
            con.close();
            
            return allscheds;
        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    /**
     * Gets a specified student's ONLY CHECKED IN schedules
     * @param studentid
     * @param classid
     * @return 
     */
    public ArrayList<Schedule> getAllCheckedinForStudent(int studentid, int classid) {
        ArrayList<Schedule> checkedins = new ArrayList<>();
        try(Connection con = super.getConnection()) {
            String query = 
                   "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
                    + "[Class].[Id] as 'ClassId',"
                    + "[Class].[Name] as 'ClassName'," //class name as string (see connections to know how we get this
                    + "[Subject].[Name] as 'SubjectName'," //subject name as string, also connections for more info
                    + "[Schedule].[Room],"
                    + "[Teacher].[Monogram] as 'TeacherName' " //teacher name as string. see connections

                    + "from [Schedule],[Class],[Subject],[Teacher],[Student],[Checked_In] " //needed tables...
                    
                    /*=======Connections======*/
                    + "where [Class].[Id] = [Schedule].[Class] " //connecting Class table and Schedule table on class id. so you will have all classnames assigned by ids correctly.
                    + "and [Subject].[Id] = [Schedule].[Subject] " //connecting Subject and Schedule table on subject id. so we can get subject name too
                    + "and [Teacher].[Id] = [Schedule].[Teacher] " //connecting Teacher table to Schdule table. same thing as above.
                    + "and [Student].[Class] = [Schedule].[Class] " //connecting Schedule and Student table on CLASS ID. therefore we get all schedules assigned to a student's class. in other words, we get the lessons only for the given student's class.
                    + "and [Checked_In].[StudentId] = [Student].[Id] "
                    + "and [Checked_In].[SchedId] = [Schedule].[Id] "
                    
                    + "and [Class].[Id] = "+classid+" and [Student].[Id] = "+studentid; //provide info. we give the student id here so the query will 'filter' results for only the given student id. giving class id is not necessary as we connected Student->Class->Schedule tables, but it's for safety reasons...
            Statement s = con.createStatement();
            
            ResultSet rs = s.executeQuery(query);
            
            
            while(rs.next()) {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                checkedins.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getInt("ClassId"),rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
            }
            con.close();
            
            return checkedins;

        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}

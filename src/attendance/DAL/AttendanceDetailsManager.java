/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
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
 * @author Kristof
 */
public class AttendanceDetailsManager {
    private SQLConnectionManager conMan = new SQLConnectionManager();
    private CurrentStudent currentStudent = CurrentStudent.getInstance();
    
    public ArrayList<Schedule> getAllCheckedin() {
        ArrayList<Schedule> checkedins = new ArrayList<>();
        try(Connection con = conMan.getConnection()) {
            String getCheckInSchedulesForStudent = 
                   "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
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
                    
                    + "and [Class].[Id] = ? and [Student].[Id] = ? "; //provide info. we give the student id here so the query will 'filter' results for only the given student id. giving class id is not necessary as we connected Student->Class->Schedule tables, but it's for safety reasons...
            PreparedStatement s = con.prepareStatement(getCheckInSchedulesForStudent);
            s.setInt(1, currentStudent.getClassid());
            s.setInt(2, currentStudent.getId());
            ResultSet rs = s.executeQuery();
            
            
            while(rs.next()) {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                checkedins.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
            }
            con.close();
            
            return checkedins;

        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public ArrayList<Schedule> getAllSchedules() {
        ArrayList<Schedule> allscheds = new ArrayList<>();
        try(Connection con = conMan.getConnection()) {
            String getAllSchedulesForStudent = 
                   "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
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
                    
                    + "and [Class].[Id] = ? and [Student].[Id] = ? "; //provide info. we give the student id here so the query will 'filter' results for only the given student id. giving class id is not necessary as we connected Student->Class->Schedule tables, but it's for safety reasons...
            
            PreparedStatement s = con.prepareStatement(getAllSchedulesForStudent);
            s.setInt(1, currentStudent.getClassid());
            s.setInt(2, currentStudent.getId());
            ResultSet rs = s.executeQuery();
            
            while(rs.next()) {
                Timestamp startTime = rs.getTimestamp("StartTime");
                Timestamp endTime = rs.getTimestamp("EndTime");
                    allscheds.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
            }
            
            con.close();
            
            return allscheds;
        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public ArrayList<Schedule> getMissedSchedules() {
        try(Connection con = conMan.getConnection()) {
            String query =
                    "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
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
                    
                    + "and [Class].[Id] = ? and [Student].[Id] = ? " //provide info. we give the student id here so the query will 'filter' results for only the given student id. giving class id is not necessary as we connected Student->Class->Schedule tables, but it's for safety reasons...
                    + "and [Schedule].[Id] not in " //here we filter the missed classes. we want to show the schedules only that are NOT IN the checked in table! so, we have to select all checkins but ONLY FOR the given student
                        + "(select [Checked_In].[SchedId] " //we need only schedule ids as we are checking it above
                        + "from [Checked_In] " //we need only Checked_In table for that.
                        + "where [Checked_In].[StudentId] = ?)"; //we want checkins for only the given student.
            //so finally we get all data for a schedule constructor. one row is one schedule. columns are displayed a we selected them in the beginning. only those schedules will display which are not in the checked in table.
                    
                    PreparedStatement s = con.prepareStatement(query);
                    s.setInt(1, currentStudent.getClassid()); //we give classid for first questionmark
                    s.setInt(2, currentStudent.getId()); //we give studentid for second questionmark
                    s.setInt(3, currentStudent.getId()); //also for thrid q. mark
                    ResultSet rs = s.executeQuery(); //get data
                    ArrayList<Schedule> missed = new ArrayList<>(); //prepare to fill data
                    Date now = new Date(); //creating datetime object with time of RIGHT NOW. so we can compare schedule times with it
                    while(rs.next()) { //for every line...
                        Timestamp startTime = rs.getTimestamp("StartTime"); //create timestamp from current schedules starttime. this is similar to Date
                        Timestamp endTime = rs.getTimestamp("EndTime"); //also for endtime.
                        if(now.after(endTime)) { //only add a schedule to 'missed' if it's already ended. so if RIGHT NOW is later than endTime.
                            missed.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getString("ClassName"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
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
    
    public int getAttendedCourses(int studentId, int classId) {
        
        try(Connection con = conMan.getConnection()) {
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
    
    public ArrayList<Schedule> getMissedSchedules(int id, int classid) {
        try(Connection con = conMan.getConnection()) {
            String query =
                    "select " //we want to have all data for the Schedule() constructor
                    + "[Schedule].[Id],"
                    + "[Schedule].[StartTime],"
                    + "[Schedule].[EndTime],"
                    + "[Class].[Name] as 'ClassId'," //class name as string (see connections to know how we get this
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
                    s.setInt(2, id); //we give studentid for second questionmark
                    s.setInt(3, id); //also for thrid q. mark
                    ResultSet rs = s.executeQuery(); //get data
                    ArrayList<Schedule> missed = new ArrayList<>(); //prepare to fill data
                    Date now = new Date(); //creating datetime object with time of RIGHT NOW. so we can compare schedule times with it
                    while(rs.next()) { //for every line...
                        Timestamp startTime = rs.getTimestamp("StartTime"); //create timestamp from current schedules starttime. this is similar to Date
                        Timestamp endTime = rs.getTimestamp("EndTime"); //also for endtime.
                        if(now.after(endTime)) { //only add a schedule to 'missed' if it's already ended. so if RIGHT NOW is later than endTime.
                            missed.add(new Schedule(rs.getInt("Id"),startTime,endTime,rs.getString("ClassId"),rs.getString("SubjectName"),rs.getString("Room"),rs.getString("TeacherName")));
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
    public ArrayList<String> getSubjects() 
    {
        try(Connection con = conMan.getConnection())
        {
            String query =
                   "select "
                   + "[Subject].[Name] "
                   + "from [Schedule],[Subject],[Class],[Student]  "
                   + "where [Schedule].[Class] = [Class].[Id]  "
                   + "and [Student].[Class] = [Class].[Id] "
                   + "and [Subject].[Id] = [Schedule].[Subject] "
                   + "and [Student].[Id] = 12 "
                   + "group by [Subject].[Name] ";
            PreparedStatement s = con.prepareStatement(query);
        }catch(SQLException e) {
            Logger.getLogger(AttendanceDetailsManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}

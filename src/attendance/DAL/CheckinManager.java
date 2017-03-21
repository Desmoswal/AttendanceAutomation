/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.Checkin;
import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
import attendance.BE.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kristof
 */
public class CheckinManager extends SQLConnectionManager {
    //private SQLConnectionManager conMan = new SQLConnectionManager();
    
    /**
     * Inserts given student's id and given schedule's in Checked_In table.
     * @param student
     * @param course 
     */
    public void insertCheckin(CurrentStudent student, Schedule schedule) {
        String query = "insert into Checked_In(StudentId,SchedId) values ("+student.getId()+","+schedule.getId()+")";
        try(Connection con = super.getConnection()) {
            Statement st = con.createStatement();
            st.execute(query);
            
            System.out.println("Check-in done for "+ student.getName() +" to "+ schedule.getSubject());
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Gets all checkins from Checked_In table and parses data in BE.
     * @return checkins
     */
    public ArrayList<Checkin> getCheckins() {
        ArrayList<Checkin> checkins = new ArrayList<>();
        String query = "select * from Checked_In";
        try(Connection con = super.getConnection()) {
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()) {
                checkins.add(new Checkin(r.getInt("StudentId"), r.getInt("SchedId")));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return checkins;
    }
    
    public void deleteCheckin(Student student, Schedule schedule) {
        String query = "DELETE FROM Checked_In WHERE StudentId = "+student.getId()+" and SchedId = " + schedule.getId();
        try(Connection con = super.getConnection()) {
            Statement st = con.createStatement();
            st.execute(query);
            
            System.out.println("Delete Check-in done for "+ student.getName() +" to "+ schedule.getSubject());
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    /*
    same as insert checkin, but this one uses Student instead of CurrentStudent, could be merged later
    */
    public void adminCheckin(Student student, Schedule schedule) {
        String query = "insert into Checked_In(StudentId,SchedId) values ("+student.getId()+","+schedule.getId()+")";
        try(Connection con = super.getConnection()) {
            Statement st = con.createStatement();
            st.execute(query);
            
            System.out.println("Check-in done for "+ student.getName() +" to "+ schedule.getSubject());
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}

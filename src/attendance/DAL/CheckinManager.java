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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kristof
 */
public class CheckinManager {
    private SQLConnectionManager conMan = new SQLConnectionManager();
    
    public void insertCheckin(CurrentStudent student, Schedule course) {
        String query = "insert into Checked_In(StudentId,SchedId) values ("+student.getId()+","+course.getId()+")";
        try(Connection con = conMan.getConnection()) {
            Statement st = con.createStatement();
            st.execute(query);
            /*PreparedStatement st = con.prepareStatement(query);
            st.setInt(1,student.getId());
            st.setInt(2, course.getId());
            st.execute();*/
            System.out.println("It runs");
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<Checkin> getCheckins() {
        ArrayList<Checkin> checkins = new ArrayList<>();
        String query = "select * from Checked_In";
        try(Connection con = conMan.getConnection()) {
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
}

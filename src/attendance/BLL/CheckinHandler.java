/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kristof
 */
public class CheckinHandler {
    private User user;
    
    /*public void doCheckin(User user,Schedule sched) throws SQLException {
        Connection con = SQLConnector.getConnection("10.176.111.31","CS2016B_16","CS2016B_16","CS2016B_16_Attendance");
        
        Statement s = con.createStatement();
        s.execute("insert into CheckIn (SchedId,StudId) values ("+sched.getId()+","+user.getId()+");");
    }*/
    
    public ArrayList<User> getCheckins() {
        return null;
    }
    
    public void unCheck(User user) {
        
    }
}

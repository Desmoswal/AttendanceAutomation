/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Model;

import attendance.BE.Student;
import attendance.BE.User;
import attendance.BLL.Passthrough;
import java.util.LinkedList;

/**
 *
 * @author Kristof
 */
public class AttendanceModel {
    
    private Passthrough pt = new Passthrough();

    public LinkedList<Student> getCheckinList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*public byte checkIfAdmin(String name) {
        return pt.checkIfAdmin(name);
    }*/
    
    public User getUser()
    {
        return new User(true);
    }
    
}

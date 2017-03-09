/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Model;

import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.BE.User;
import attendance.BLL.Passthrough;
import java.util.LinkedList;
import java.util.List;

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
    
    public List<Student> getStudents()
    {
        return pt.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return pt.getTeachers();
    }
}

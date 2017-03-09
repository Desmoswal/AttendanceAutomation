/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Model;

import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.BLL.DataManager;
import attendance.BLL.LoginHandler;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kristof
 */
public class AttendanceModel {
    
    private DataManager dataManager = new DataManager();
    private LoginHandler loginHandler = new LoginHandler();

    public LinkedList<Student> getCheckinList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*public byte checkIfAdmin(String name) {
        return pt.checkIfAdmin(name);
    }*/
    
    public List<Student> getStudents()
    {
        return dataManager.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return dataManager.getTeachers();
    }
    
    public int checkLogin(String uname, String pass) {
        return loginHandler.checkLogin(uname, pass);
    }
}

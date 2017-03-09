/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.DAL.LoginManager;
import java.util.List;

/**
 *
 * @author Desmoswal
 */
public class DataManager
{
    private LoginManager loginManager = new LoginManager();
    
    
    public List<Student> getStudents()
    {
        return loginManager.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return loginManager.getTeachers();
    }
}

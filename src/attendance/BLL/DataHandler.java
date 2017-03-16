/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.BLL;

import attendance.BE.Student;
import attendance.BE.Teacher;
import attendance.BE.Class;
import attendance.DAL.DataManager;
import attendance.DAL.LoginManager;
import java.util.List;

/**
 *
 * @author Desmoswal
 */
public class DataHandler
{
    private LoginManager loginManager = new LoginManager();
    private DataManager dataManager = new DataManager();
    
    
    public List<Student> getStudents()
    {
        return loginManager.getStudents();
    }
    
    public List<Teacher> getTeachers() {
        return loginManager.getTeachers();
    }
    
    public List<Class> getClasses()
    {
        return dataManager.getClasses();
    }
    
    public List<Student> getStudentsByClass(int classId)
    {
        return dataManager.getStudentsByClass(classId);
    }
}

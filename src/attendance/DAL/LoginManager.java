/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.Student;
import attendance.BE.Teacher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Desmoswal
 */
public class LoginManager
{
    SQLConnectionManager conManager = new SQLConnectionManager();
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    
    public ArrayList<Student> getStudents()
    {
        buildStudents();
        return students;
    }
    
    public ArrayList<Teacher> getTeachers() {
        buildTeachers();
        return teachers;
    }
    
    /**
     * Gets all students from database, fills the local list. You can build and get this list by getStudents().
     */
    private void buildStudents() {
        students = new ArrayList<>();
        try(Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                students.add(new Student(Integer.parseInt(rs.getString("Id")),rs.getString("Name"),rs.getString("Username"),rs.getString("Password"),rs.getString("Email"),Integer.parseInt(rs.getString("Class"))));
            }
            
            con.close();
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
        }
    }
    
    /**
     * Gets all teachers from database, fills the local list. You can build and get this list by getTeachers().
     */
    private void buildTeachers() {
        teachers = new ArrayList<>();
        try(Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Teacher]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                teachers.add(new Teacher(Integer.parseInt(rs.getString("Id")),rs.getString("Name"),rs.getString("Monogram"),rs.getString("Email"),rs.getString("Password")));
            }
            
            con.close();
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
        }
        
    }
}

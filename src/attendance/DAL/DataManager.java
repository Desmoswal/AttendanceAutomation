/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

import attendance.BE.Class;
import attendance.BE.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Desmoswal
 */
public class DataManager extends SQLConnectionManager
{
    
    //private SQLConnectionManager conMan = new SQLConnectionManager();
    private ArrayList<Student> students = new ArrayList();
    
    /**
     * Gets all classes from Class table and parses data in BE.
     * @return checkins
     */
    public ArrayList<Class> getClasses() {
        ArrayList<Class> classes = new ArrayList<>();
        String query = "select * from [Class]";
        try(Connection con = super.getConnection()) {
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()) {
                classes.add(new Class(r.getInt("Id"), r.getString("Name")));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return classes;
    }
    
    public ArrayList<Student> getStudentsByClass(int classId)
    {
        buildStudent(classId);
        return students;
    }
    
    /**
     * Gets specific student from database, fills the local list. You can build and get this list by getStudentsByClass().
     */
    private void buildStudent(int classId) {
        students = new ArrayList<>();
        try(Connection con = super.getConnection())
        {
            String query = "SELECT Student.* FROM Student where Student.Class = "+ classId;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                students.add(new Student(Integer.parseInt(rs.getString("Id")),rs.getString("Name"),rs.getString("Username"),rs.getString("Password"),rs.getString("Email"),Integer.parseInt(rs.getString("Class"))));
            }
            System.out.println();
            con.close();
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
        }
    }
}

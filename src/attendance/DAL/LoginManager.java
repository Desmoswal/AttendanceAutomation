/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.DAL;

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
    SQLConnectionManager conManager;
    
    public ArrayList<String> getStudents()
    {
        try(Connection con = conManager.getConnection())
        {
            String query = "SELECT Username FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<String> students = new ArrayList<>();
            while(rs.next())
            {
                String studentString = "";
                //studentString += rs.getString("id") + " ";
                //studentString +=rs.getString("name") + " ";
                //studentString +=rs.getString("classid");
                studentString += rs.getString("username");
                students.add(studentString);
                System.out.println(studentString);
            }
            
            return students;
            
            
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import attendance.DAL.SQLConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Desmoswal
 */
public class Attendance extends Application
{
    SQLConnectionManager conManager;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        conManager = new SQLConnectionManager();
        
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/LogIn.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("attendance/EASV.png"));
        stage.show();
        
        //getStudents();
        //getUsernames();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    /*public ArrayList<String> getStudents()
    {
        try(Connection con = conManager.getConnection())
        {
            String query = "SELECT * FROM [Student] where id = 1";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<String> students = new ArrayList<>();
            while(rs.next())
            {
                String studentString = "";
                studentString += rs.getString("id") + " ";
                studentString +=rs.getString("name") + " ";
                //studentString +=rs.getString("classid");
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
    
    public ArrayList<String> getUsernames()
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
    }*/
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

import attendance.BE.CurrentStudent;
import attendance.DAL.SQLConnectionManager;
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
    CurrentStudent cur = CurrentStudent.getInstance();
    
    @Override
    public void start(Stage stage) throws Exception
    {
        conManager = new SQLConnectionManager();
        
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/LogIn.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("attendance/EASV.png"));
        stage.show();
               
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
  
}

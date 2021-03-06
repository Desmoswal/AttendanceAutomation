/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentStudent;
import attendance.GUI.Model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class StudentMenuController implements Initializable
{

    @FXML
    private Button btnCourses;
    @FXML
    private Button btnOnline;
    @FXML
    private Button btnMyAttendance;
    @FXML
    private Button btnLogOut;
    
    private MainViewController parentCon;
    
    AttendanceModel model = new AttendanceModel();
    
    CurrentStudent currentStudent = CurrentStudent.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void pressedMenuButton(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();
        System.out.println("pressed button: "+event.getSource());
        //opens and loads content depending on pressed menu button
        switch(pressedButton.getId()) {
            case "btnCourses":
                try {
                    parentCon.openItem("/attendance/GUI/View/TodaysCourses.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                   Logger.getLogger(StudentMenuController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "btnMyAttendance":
                try {
                    parentCon.openItem("/attendance/GUI/View/AttendanceDetails.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                    Logger.getLogger(StudentMenuController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "btnOnline":
                try {
                    parentCon.openItem("/attendance/GUI/View/OnlineUsers.fxml");
                } catch(IOException e) {
                    System.out.println("FXML probably not found");
                    Logger.getLogger(StudentMenuController.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            case "btnLogOut":
                //do logout
                model.setOnline(currentStudent, 0);
                System.out.println(currentStudent.getName() + " is now offline.");
                System.exit(0);
                break;
        }
    }
    
    /**
     * Sets MainViewController in the menu so menu can request content load in MainView.
     * @param pc 
     */
    public void setParentController(MainViewController pc) {
            this.parentCon = pc;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentStudent;
import attendance.BE.CurrentTeacher;
import attendance.GUI.Model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class MainViewController implements Initializable
{

    @FXML
    private GridPane MainGrid;
    @FXML
    private AnchorPane paneMenu;
    @FXML
    private Label lblName;
    @FXML
    private Label lblDate;
    @FXML
    private AnchorPane paneItem;
    @FXML
    private AnchorPane anchorpane;
    
    private AttendanceModel model = new AttendanceModel();
    private static UserType user = null;
    public static enum UserType {STUDENT,TEACHER};
    private CurrentStudent currentStudent = CurrentStudent.getInstance();
    private CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    private Date date = new Date();
    private String[] weekdays = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {     //loading sidebar menu depending on usertype
              if(user.equals(UserType.TEACHER))
              {
                  lblName.setText(currentTeacher.getName());
                  int y = date.getYear()+1900;
                  int m = date.getMonth()+1;
                  lblDate.setText(weekdays[date.getDay()]+", "+date.getDate()+"-"+m+"-"+y);
                  
                  FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminMenu.fxml"));
                  AnchorPane menuPane = menuLoader.load();
                  AdminMenuController menuController = menuLoader.getController();
                  menuController.setParentController(this); //gives MainViewController to menu, so the menu can load content if a button was pressed
                  paneMenu.getChildren().add(menuPane);
                                    
                  FXMLLoader itemLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminToday.fxml")); //default screen
                  AnchorPane itemPane = itemLoader.load();
                  AdminTodayController itemController = itemLoader.getController();
                  paneItem.getChildren().add(itemPane);
              }
              else if(user.equals(UserType.STUDENT))
              {
                  lblName.setText(currentStudent.getName());
                  int y = date.getYear()+1900;
                  int m = date.getMonth()+1;
                  lblDate.setText(weekdays[date.getDay()]+", "+date.getDate()+"-"+m+"-"+y);
                  
                  FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/StudentMenu.fxml"));
                  AnchorPane menuPane = menuLoader.load();
                  StudentMenuController menuController = menuLoader.getController();
                  menuController.setParentController(this); //gives MainViewController to menu, so the menu can load content if a button was pressed
                  paneMenu.getChildren().add(menuPane);
                  
                  
                  FXMLLoader itemLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/TodaysCourses.fxml")); //default screen
                  AnchorPane itemPane = itemLoader.load();
                  TodaysCoursesController itemController = itemLoader.getController();
                  paneItem.getChildren().add(itemPane);
              }
            
        } catch (IOException ex)
        {
            //we need the logger so we can see the actual error and not just "FXMLLoader error."
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    /**
     * Opens and loads fxml in the right side. This is used by menubuttons to load content.
     * @param url
     * @throws IOException 
     */
    public void openItem(String url) throws IOException {
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(url));
        AnchorPane itemPane = itemLoader.load();
        Object controller = itemLoader.getController();
        paneItem.getChildren().clear();
        paneItem.getChildren().add(itemPane);
    }
    
    /**
     * Sets usertype before this controller is loaded.
     * @param set 
     */
    public static void setUserType(UserType set) {
        user = set;
    }
}

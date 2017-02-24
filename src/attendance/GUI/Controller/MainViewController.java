/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.GUI.Model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
    private AnchorPane paneItem;
    @FXML
    private AnchorPane anchorpane;
    
    private AttendanceModel model = new AttendanceModel();
    private static String user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {     
              if(user.equals("admin"))
              {
                  FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminMenu.fxml"));
                  AnchorPane menuPane = menuLoader.load();
                  AdminMenuController menuController = menuLoader.getController();
                  menuController.setParentController(this);
                  paneMenu.getChildren().add(menuPane);
                  
                  FXMLLoader itemLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminToday.fxml"));
                  AnchorPane itemPane = itemLoader.load();
                  AdminTodayController itemController = itemLoader.getController();
                  paneItem.getChildren().add(itemPane);
              }
              else if(user.equals("student"))
              {
                  FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/StudentMenu.fxml"));
                  AnchorPane menuPane = menuLoader.load();
                  StudentMenuController menuController = menuLoader.getController();
                  menuController.setParentController(this);
                  paneMenu.getChildren().add(menuPane);
                  
                  FXMLLoader itemLoader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/TodaysCourses.fxml"));
                  AnchorPane itemPane = itemLoader.load();
                  TodaysCoursesController itemController = itemLoader.getController();
                  paneItem.getChildren().add(itemPane);
              }
            
        } catch (IOException ex)
        {
            System.out.println(ex);
        } 

    }    
    public void openItem(String url) throws IOException {
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(url));
        AnchorPane itemPane = itemLoader.load();
        Object controller = itemLoader.getController();
        paneItem.getChildren().clear();
        paneItem.getChildren().add(itemPane);
    }
    
    public static void setUserDemo(String set) {
        user = set;
    }
}

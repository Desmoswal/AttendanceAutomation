/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        try
        {
              AnchorPane menuPane = FXMLLoader.load(getClass().getResource("/attendance/GUI/View/StudentMenu.fxml"));
              paneMenu.getChildren().add(menuPane);
              
              AnchorPane itemPane = FXMLLoader.load(getClass().getResource("/attendance/GUI/View/AttendanceDetails.fxml"));
              paneItem.getChildren().add(itemPane);
            
        } catch (IOException ex)
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        } 

        
    }    
}

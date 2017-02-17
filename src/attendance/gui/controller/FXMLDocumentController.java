/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author boldi
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnCheckIn;
    @FXML
    private Button btnMyAttandance;
    
    Label lblWelcome = new Label();
    ListView courseList = new ListView();
    
    @FXML
    private Button btnCourses;
    @FXML
    private Button btnOnline;
    @FXML
    private Button btnLogOut;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private GridPane MainGrid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }    
    @FXML
    private void TodaysButtonAction (ActionEvent event)
    {
    
    MainGrid.add(courseList, 1, 1);
    }
}

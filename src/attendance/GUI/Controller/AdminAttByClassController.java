/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class AdminAttByClassController implements Initializable {

    @FXML
    private TableView<?> tblClasses;
    @FXML
    private Label lblAvgAttofClass;
    @FXML
    private Label lblMostMissedCourse;
    @FXML
    private Label lblMostAttendCourse;
    @FXML
    private Label lblMostMissedStud;
    @FXML
    private Label lblMostMissedStudCourse;
    @FXML
    private Label lblMostAttStud;
    @FXML
    private Label lblMostAttStudCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

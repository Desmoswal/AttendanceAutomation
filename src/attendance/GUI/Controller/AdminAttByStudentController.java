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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class AdminAttByStudentController implements Initializable {

    @FXML
    private TableView<?> tblClasses;
    @FXML
    private TableView<?> tblStudents;
    @FXML
    private ComboBox<?> cmbStudClassSelect;
    @FXML
    private Label lblStudAvgAtt;
    @FXML
    private Label lblStudAttCourses;
    @FXML
    private Label lblStudMissedCourses;
    @FXML
    private Label lblStudMostMissedCourse;
    @FXML
    private Label lblStudMostAttCourse;
    @FXML
    private Label lblStudMostMissedWDay;
    @FXML
    private Label lblStudAttPercentSelected;
    @FXML
    private Label lblStudAttSelected;
    @FXML
    private Label lblStudMissedSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

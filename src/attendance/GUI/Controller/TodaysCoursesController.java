/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class TodaysCoursesController implements Initializable
{

    @FXML
    private TableView<?> tblCourse;
    @FXML
    private TableColumn<?, ?> colTime;
    @FXML
    private TableColumn<?, ?> colClass;
    @FXML
    private TableColumn<?, ?> colTeacher;
    @FXML
    private TableColumn<?, ?> colRoom;
    @FXML
    private ComboBox<?> cmbCourse;
    @FXML
    private Button btnCheckIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    
}

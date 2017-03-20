/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Student;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminCheckinController implements Initializable
{

    @FXML
    private Button btnClose;
    @FXML
    private Label lblClass;
    @FXML
    private Label lblCourse;
    @FXML
    private TableView<Student> tblAttending;
    @FXML
    private TableColumn<Student, String> colName;
    
    
    private AttendanceModel model = new AttendanceModel();
    private LinkedList<Student> checkinList;
    private LinkedList<CheckBox> checkinboxes = new LinkedList<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {    
        setTableProperties();
    }    

    private void setTableProperties()
    {
        colName.setCellValueFactory(new PropertyValueFactory("name"));
    }
    
    public void setTableItems(int classId)
    {
        tblAttending.setItems(FXCollections.observableArrayList(model.getStudentsByClass(classId)));
    }
    
    @FXML
    private void pressedOnTable(MouseEvent event)
    {
        
    }
    
    @FXML
    private void closeButtonPressed()
    {
        Stage curStage = (Stage) btnClose.getScene().getWindow();
        curStage.close();
    }
    
}

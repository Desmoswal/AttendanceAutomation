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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminCheckinController implements Initializable
{

    @FXML
    private ListView<?> listStudents;
    @FXML
    private CheckBox chkStudent;
    @FXML
    private Button btnClose;
    @FXML
    private Label lblClass;
    @FXML
    private Label lblCourse;

    private AttendanceModel model = new AttendanceModel();
    private LinkedList<Student> checkinList;
    private LinkedList<CheckBox> checkinboxes = new LinkedList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        checkinList = model.getCheckinList();
        if(!checkinList.isEmpty()) {
            for (Student student : checkinList) {
                CheckBox cb = new CheckBox(student.getName());
                if(student.isCheckedIn()) {
                    cb.selectedProperty().set(true);
                } else {
                    cb.selectedProperty().set(false);
                }
                checkinboxes.add(cb);
            }
            listStudents.getChildrenUnmodifiable().addAll(checkinboxes);
        }
        
        
    }    
    
}

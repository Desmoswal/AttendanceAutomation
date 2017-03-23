/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Class;
import attendance.BE.Student;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class AdminAttByClassController implements Initializable
{

    @FXML
    private TableView<Class> tblClasses;
    @FXML
    private TableColumn<Class, String> colClass;
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

    AttendanceModel model = new AttendanceModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO

        setTableProperties();

        //Load Classes into tableview
        tblClasses.setItems(FXCollections.observableArrayList(model.getClasses()));
    }

    private void setTableProperties()
    {
        colClass.setCellValueFactory(new PropertyValueFactory("name"));
    }

    @FXML
    private void pressedOnTableClasses(MouseEvent event)
    {
        Class selected = null;

        if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
        {
            selected = tblClasses.getSelectionModel().getSelectedItem();

            System.out.println("Selected Class: " + selected.getId() + " " + selected.getName());

            lblAvgAttofClass.setText(model.getAvgAttForClass(selected.getId())+"%");
            lblMostMissedCourse.setText(model.getMostMissedCourseForClass(selected.getId()));
            lblMostAttendCourse.setText(model.getMostAttCourseForClass(selected.getId()));
            lblMostMissedStud.setText(model.getMostMissedStudentForClass(selected.getId()).getName());
            lblMostMissedStudCourse.setText(model.getMostMissedStudentsMostMissedCourse(selected.getId()));
            Student student = model.getMostAttStudentForClass(selected.getId());
            if(student != null) {
                lblMostAttStud.setText(student.getName());
            } else {
                lblMostAttStud.setText("No attendance data for class.");
            }
            
            lblMostAttStudCourse.setText(model.getMostAttStudentMostAttCourse(selected.getId()));
        }
    }

}

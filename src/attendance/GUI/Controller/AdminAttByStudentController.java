/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.BE.Class;
import attendance.DAL.AttendanceDetailsManager;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class AdminAttByStudentController implements Initializable
{

    @FXML
    private TableView<Class> tblClasses;
    @FXML
    private TableView<Student> tblStudents;
    @FXML
    private TableColumn<Class, String> colClass;
    @FXML
    private TableColumn<Student, String> colStudent;
    @FXML
    private ComboBox<?> cmbStudClassSelect;
    @FXML
    private Label lblStudAvgAtt;
    @FXML
    private Label lblStudAttCourses;
    @FXML
    private Label lblStudMissedCourses;
    @FXML
    private Label lblStudMostMissedWDay;
    @FXML
    private Label lblStudAttPercentSelected;
    @FXML
    private Label lblStudAttSelected;
    @FXML
    private Label lblStudMissedSelected;

    private AttendanceModel model = new AttendanceModel();
    
    private AttendanceDetailsManager attdetailsmanager = new AttendanceDetailsManager();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cmbStudClassSelect.setButtonCell(new ListCell()
        {
            @Override
            protected void updateItem(Object item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty || item == null)
                {
                    // styled like -fx-prompt-text-fill:
                    setStyle("-fx-text-fill: white;");
                } else
                {
                    setStyle("-fx-text-fill: white;");
                    setText(item.toString());
                }
            }
        });

        setTableProperties();

        //Load Classes into tableview
        tblClasses.setItems(FXCollections.observableArrayList(model.getClasses()));
    }

    /**
     * setting table's properties
     */
    private void setTableProperties()
    {
        colStudent.setCellValueFactory(new PropertyValueFactory("name"));
        colClass.setCellValueFactory(new PropertyValueFactory("name"));
    }

    @FXML
    private void pressedOnTableClasses(MouseEvent event)
    {
        Class selected = null;

        if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
        {
            selected = tblClasses.getSelectionModel().getSelectedItem();

            //Get Students by selected class
            tblStudents.setItems(FXCollections.observableArrayList(model.getStudentsByClass(selected.getId())));

            System.out.println("Selected Class: " + selected.getId() + " " + selected.getName());
        }
    }

    @FXML
    private void pressedOnTableStudents(MouseEvent event)
    {
        Student selected = null;

        if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
        {
            selected = tblStudents.getSelectionModel().getSelectedItem();
            System.out.println("Selected Student: " + selected.getName());
            System.out.println("Selected Student ID: " + selected.getId());
        }
        //--only for test purposes, we just need to change the methods
        int missed = attdetailsmanager.getMissedSchedules(selected.getId(), selected.getClassid()).size();
        System.out.println("Missed Classes: "+ missed);
        
        int attended = attdetailsmanager.getAttendedCourses(selected.getId(), selected.getClassid());
        System.out.println("Attended Classes: "+ attended);
        
        int totalClasses = missed + attended;
        System.out.println("Total classes: " + totalClasses);
        
        float avgAtt = (float)attended / (float)totalClasses * 100 ;
        
        lblStudAvgAtt.setText(String.valueOf(avgAtt));
        lblStudAttCourses.setText(String.valueOf(attdetailsmanager.getAttendedCourses(selected.getId(), selected.getClassid())));
        lblStudMissedCourses.setText(String.valueOf(attdetailsmanager.getMissedSchedules(selected.getId(), selected.getClassid()).size()));
        lblStudMostMissedWDay.setText(selected.getName());
        
        //--Only after combo box selection
        //lblStudAttPercentSelected.setText(selected.getName());
        //lblStudAttSelected.setText(selected.getName());
        //lblStudMissedSelected.setText(selected.getName());
    }
}

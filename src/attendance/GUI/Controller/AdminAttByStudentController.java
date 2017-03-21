/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.BE.Class;
import attendance.BE.CurrentTeacher;
import attendance.DAL.AttendanceDetailsManager;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
    private CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    
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
        /*int missed = model.getMissedSchedulesForStudent(selected.getId(), selected.getClassid()).size();
        System.out.println("Missed Classes: "+ missed);
        
        int attended = model.getAllCheckedinForStudent(selected.getId(), selected.getClassid()).size();
        System.out.println("Attended Classes: "+ attended);
        
        int totalClasses = missed + attended;
        System.out.println("Total classes: " + totalClasses);
        
        float avgAtt = (float)attended / (float)totalClasses * 100 ;*/
        
        lblStudAvgAtt.setText(model.getTotalAttPercentForStudent(selected.getId(), selected.getClassid())+"%");
        lblStudAttCourses.setText(""+model.getAllCheckedinForStudent(selected.getId(), selected.getClassid()).size());
        lblStudMissedCourses.setText(""+model.getMissedSchedulesForStudent(selected.getId(), selected.getClassid()).size());
        lblStudMostMissedWDay.setText(selected.getName());
        
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (Schedule schedule : model.getMissedSchedulesForStudent(selected.getId(), selected.getClassid())) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(schedule.getStartTime());
            int key = cal.get(Calendar.DAY_OF_WEEK);
            if(hm.containsKey(key)) {
                int value = hm.get(key);
                hm.put(key,value+1);
            } else {
                hm.put(key, 1);
            }
        }
        int max = 0;
        for(int value : hm.values()) {
            if(value > max) {
                max = value;
            }
        }
        
        //--Only after combo box selection
        //lblStudAttPercentSelected.setText(selected.getName());
        //lblStudAttSelected.setText(selected.getName());
        //lblStudMissedSelected.setText(selected.getName());
    }
}

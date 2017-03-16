/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
import attendance.BLL.ScheduleManager;
import attendance.DAL.SQLConnectionManager;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class TodaysCoursesController implements Initializable
{

    SQLConnectionManager conManager;
    
    ScheduleManager scheduleManager = new ScheduleManager();

    @FXML
    private TableView<Schedule> tblCourse;
    @FXML
    private TableColumn<Schedule, String> colTime;
    @FXML
    private TableColumn<Schedule, String> colClass;
    @FXML
    private TableColumn<Schedule, String> colTeacher;
    @FXML
    private TableColumn<Schedule, String> colRoom;
    @FXML
    private ComboBox<?> cmbCourse;
    @FXML
    private Button btnCheckIn;
    
    private Schedule selected = null;
    private CurrentStudent currentStudent = CurrentStudent.getInstance();
    private AttendanceModel model = new AttendanceModel();
    private boolean checkedin = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //sets combobox's color
        cmbCourse.setButtonCell(new ListCell()
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
        setTableItems();
        changeCheckedIn();
    }
    
    /**
     * setting table's properties
     */
    private void setTableProperties() {
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colClass.setCellValueFactory(new PropertyValueFactory("classId"));
        colTeacher.setCellValueFactory(new PropertyValueFactory("teacher"));
        colRoom.setCellValueFactory(new PropertyValueFactory("room"));
    }
    
    /**
     * sets schedules in table
     */
    private void setTableItems() {
        ArrayList<Schedule> schedule = new ArrayList();
        //System.out.println(scheduleManager.getSchedules());
        //tblCourse.setItems(FXCollections.observableArrayList(scheduleManager.getSchedules()));
        System.out.println("Today's scheds: "+model.getTodaysSchedules());
        tblCourse.setItems(FXCollections.observableArrayList(model.getTodaysSchedules()));
    }
    
    @FXML
    private void pressedOnTable(MouseEvent event) {
        if(event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            selected = tblCourse.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    private void pressedCheckin(ActionEvent event) {
        if(selected != null) {
            model.doCheckin(currentStudent,selected);
        }
        changeCheckedIn();
    }
    
    //just in case....
    private void updateTable() {
        tblCourse.setItems(FXCollections.observableArrayList(scheduleManager.getSchedules()));
        tblCourse.refresh();
    }
    
    /**
     * Changes courses row color which are already checked in.
     */
    private void changeCheckedIn() {
        tblCourse.setRowFactory(tv -> new TableRow<Schedule>() {
            AttendanceModel model = new AttendanceModel();
            @Override
            public void updateItem(Schedule item,boolean empty) {
                super.updateItem(item,empty);
                if(item == null) {
                    setStyle("");
                }
                if(item != null) { //avoiding nullpointer exception
                    if(model.getCheckedInSchedules().contains(item.getId()))
                        setStyle("-fx-background-color:rgba(44, 255, 44, 0.5);");
                }
            }
        });
    }
}

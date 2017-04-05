/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
import attendance.BLL.ScheduleHandler;
import attendance.DAL.SQLConnectionManager;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    
    ScheduleHandler scheduleHandler = new ScheduleHandler();

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
    private Button btnCheckIn;
    
    private Schedule selected = null;
    private CurrentStudent currentStudent = CurrentStudent.getInstance();
    private AttendanceModel model = new AttendanceModel();
    private boolean checkedin = false;
    List<Integer> checkedinIds = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        updateCheckedInIds();
        setTableProperties();
        setTableItems();
        changeCheckedIn();
    }
    
    /**
     * setting table's properties
     */
    private void setTableProperties() {
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colClass.setCellValueFactory(new PropertyValueFactory("className"));
        colTeacher.setCellValueFactory(new PropertyValueFactory("teacher"));
        colRoom.setCellValueFactory(new PropertyValueFactory("room"));
    }
    
    /**
     * sets schedules in table
     */
    private void setTableItems() {
        ArrayList<Schedule> schedule = new ArrayList();

        System.out.println("Today's scheds: "+model.getTodaysSchedulesForStudent(currentStudent.getId(),currentStudent.getClassid()));
        tblCourse.setItems(FXCollections.observableArrayList(model.getTodaysSchedulesForStudent(currentStudent.getId(), currentStudent.getClassid())));
    }
    
    @FXML
    private void pressedOnTable(MouseEvent event) {
        if(event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            selected = tblCourse.getSelectionModel().getSelectedItem();
            changeCheckinButton(selected);
        }
    }
    
    @FXML
    private void pressedCheckin(ActionEvent event) {
        if(selected != null) {
            model.doCheckin(currentStudent,selected);
            changeCheckinButton(selected);
        }
        updateCheckedInIds();
        changeCheckedIn();
        
    }
    
    //just in case....
    private void updateTable() {
        tblCourse.setItems(FXCollections.observableArrayList(model.getTodaysSchedulesForStudent(currentStudent.getId(), currentStudent.getClassid())));
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
                    if(checkedinIds.contains(item.getId()))
                    {
                        setStyle("-fx-background-color:rgba(44, 255, 44, 0.5);");
                    }
                    if(item.isCanceled()) {
                        setStyle("-fx-background-color:rgba(255, 0, 0, 0.5);");
                    }
                    
                }
            }
        });
    }
    
    private void updateCheckedInIds() {
        for (Schedule schedule : model.getAllCheckedinForStudent(currentStudent.getId(), currentStudent.getClassid())) {
                checkedinIds.add(schedule.getId());
        }
    }
    
    private void changeCheckinButton(Schedule selected) {
        if(selected.isCanceled() || checkedinIds.contains(selected.getId())) {
            btnCheckIn.setDisable(true);
        } else {
            btnCheckIn.setDisable(false);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminTodayController implements Initializable
{

    @FXML
    private TableColumn<Schedule, String> colTime;
    @FXML
    private TableColumn<Schedule, String> colCourseName;
    @FXML
    private TableColumn<Schedule, String> colClass;
    @FXML
    private TableColumn<Schedule, String> colRoom;
    @FXML
    private TableView<Schedule> tblToday;

    private AttendanceModel model = new AttendanceModel();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setTableProperties();
        setTableItems();
    }    
    
    private void setTableProperties() {
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colCourseName.setCellValueFactory(new PropertyValueFactory("name"));
        colClass.setCellValueFactory(new PropertyValueFactory("classId"));
        colRoom.setCellValueFactory(new PropertyValueFactory("room"));
    }
    
    private void setTableItems()
    {
        tblToday.setItems(FXCollections.observableArrayList(model.getTeacherSchedule()));
    }
    
     @FXML
    private void pressedOnTable(MouseEvent event) {
        Schedule selected = null;
        
        if(event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            selected = tblToday.getSelectionModel().getSelectedItem();
            
            System.out.println("Selected Schedule: " + selected.getId());
        }
    }
    
}

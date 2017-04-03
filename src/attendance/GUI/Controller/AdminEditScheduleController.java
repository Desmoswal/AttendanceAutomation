/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.BE.CurrentTeacher;
import attendance.BE.Schedule;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminEditScheduleController implements Initializable
{

    @FXML
    private TableView<Schedule> tblEdit;
    @FXML
    private TableColumn<Schedule, String> colDate;
    @FXML
    private TableColumn<Schedule, String> colTime;
    @FXML
    private TableColumn<Schedule, String> colCourse;
    @FXML
    private TableColumn<Schedule, String> colSubject;
    @FXML
    private TableColumn<Schedule, String> colRoom;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    AttendanceModel model = new AttendanceModel();

    Schedule selected;

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
        colDate.setCellValueFactory(value -> {
            StringProperty date = new SimpleStringProperty();
            int month = value.getValue().getStartTime().getMonth()+1;
            int year = value.getValue().getStartTime().getYear()+1900;
            date.set(year+" - " + month+ " - "+ value.getValue().getStartTime().getDate()+"");
            System.out.println(value.getValue().getStartTime()+" ----------- 1a564d86qw65d4a8s9d");
            System.out.println(date);
            return  date;
            
        });
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colCourse.setCellValueFactory(new PropertyValueFactory("className"));
        colSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        colRoom.setCellValueFactory(new PropertyValueFactory("room"));
    }
    
    private void setTableItems()
    {
        tblEdit.setItems(FXCollections.observableArrayList(model.getAllSchedulesForTeacher(currentTeacher.getId())));
    } 

    @FXML
    private void pressedOnTable(MouseEvent event) {
        selected = null;
        
        if(event.isPrimaryButtonDown() && event.getClickCount() == 1) {
            selected = tblEdit.getSelectionModel().getSelectedItem();
            
            System.out.println("Selected Schedule: " + selected.getId());
        }
        
        else if(event.isPrimaryButtonDown() && event.getClickCount() == 2)
        {
            try
            {
                
                selected = tblEdit.getSelectionModel().getSelectedItem();
                Stage primStage = (Stage) tblEdit.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminEditSchedulePopup.fxml"));
                
                AdminEditSchedulePopupController.setThisSchedule(selected);
                
                Parent root = loader.load();

                // Fetches controller from view
                AdminEditSchedulePopupController controller = loader.getController();
                controller.setController(this);
                System.out.println("selected for the controller: " + " classId: " + selected.getClassId() + ", Id: " + selected.getId());
                //controller.setTableItems(selected.getClassId());
                //controller.setLabels(selected.getClassId(), selected.getSubject());
                controller.setText();
                controller.setCurrentTeacher(currentTeacher);
                // Sets new stage as modal window
                Stage stageView = new Stage();
                stageView.setScene(new Scene(root));

                stageView.initModality(Modality.WINDOW_MODAL);
                stageView.initOwner(primStage);

                stageView.show();
            } 
            
            catch (Exception e)
            {
                System.out.println(e);
            }

        }
    }
    
    public void refreshTable()
    {
        tblEdit.refresh();
    }

    @FXML
    private void onAddButtonPressed(ActionEvent event)
    {
        try
            {
                
                Stage primStage = (Stage) tblEdit.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminEditSchedulePopup.fxml"));
                
                
                Parent root = loader.load();

                // Fetches controller from view
                AdminEditSchedulePopupController controller = loader.getController();
                controller.setController(this);
                //System.out.println("selected for the controller: " + " classId: " + selected.getClassId() + ", Id: " + selected.getId());
                //controller.setTableItems(selected.getClassId());
                //controller.setLabels(selected.getClassId(), selected.getSubject());
                //controller.setText();
                controller.setCurrentTeacher(currentTeacher);
                // Sets new stage as modal window
                Stage stageView = new Stage();
                stageView.setScene(new Scene(root));

                stageView.initModality(Modality.WINDOW_MODAL);
                stageView.initOwner(primStage);

                stageView.show();
            } 
            
            catch (Exception e)
            {
                System.out.println(e);
            }
        
    }

    @FXML
    private void onUpdateButtonPressed(ActionEvent event)
    {
        selected = tblEdit.getSelectionModel().getSelectedItem();
 
        System.out.println("Selected Schedule: " + selected.getId());

            try
            {
                
                selected = tblEdit.getSelectionModel().getSelectedItem();
                Stage primStage = (Stage) tblEdit.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/AdminEditSchedulePopup.fxml"));
                
                AdminEditSchedulePopupController.setThisSchedule(selected);
                
                Parent root = loader.load();

                // Fetches controller from view
                AdminEditSchedulePopupController controller = loader.getController();
                controller.setController(this);
                System.out.println("selected for the controller: " + " classId: " + selected.getClassId() + ", Id: " + selected.getId());
                //controller.setTableItems(selected.getClassId());
                //controller.setLabels(selected.getClassId(), selected.getSubject());
                controller.setText();
                controller.setCurrentTeacher(currentTeacher);
                // Sets new stage as modal window
                Stage stageView = new Stage();
                stageView.setScene(new Scene(root));

                stageView.initModality(Modality.WINDOW_MODAL);
                stageView.initOwner(primStage);

                stageView.show();
            } 
            
            catch (Exception e)
            {
                System.out.println(e);
            }
        
    }

    @FXML
    private void onDeleteButtonPressed(ActionEvent event)
    {
        selected = tblEdit.getSelectionModel().getSelectedItem();
        
        model.deleteSchedule(selected.getId());
        
        System.out.println("Deleted schedule: " + selected.getId());
    }
}

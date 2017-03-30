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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TableColumn<Schedule, String> colTime;
    @FXML
    private TableColumn<Schedule, String> colCourse;
    @FXML
    private TableColumn<Schedule, String> colRoom;

    
    CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    AttendanceModel model = new AttendanceModel();
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
        colCourse.setCellValueFactory(new PropertyValueFactory("className"));
        //colClass.setCellValueFactory(new PropertyValueFactory("subject"));
        colRoom.setCellValueFactory(new PropertyValueFactory("room"));
    }
    
    private void setTableItems()
    {
        tblEdit.setItems(FXCollections.observableArrayList(model.getAllSchedulesForTeacher(currentTeacher.getId())));
    } 

    @FXML
    private void pressedOnTable(MouseEvent event) {
        Schedule selected = null;
        
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
}

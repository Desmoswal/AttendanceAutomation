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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AttendanceDetailsController implements Initializable
{

    @FXML
    private TableView<Schedule> tblMissed;
    @FXML
    private TableColumn<Schedule, String> colDate;
    @FXML
    private TableColumn<Schedule, String> colTime;
    @FXML
    private TableColumn<Schedule, String> colTeacher;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblAttendance;
    @FXML
    private Label lblMissed;
    @FXML
    private ComboBox<?> cmbClass;
    @FXML
    private Label lblMissedTotal;
    
    private AttendanceModel model = new AttendanceModel();
    @FXML
    private TableColumn<Schedule, String> colSubject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lblTotal.setText(""+model.getAllCheckedIn());
        cmbClass.setButtonCell(new ListCell() {
            @Override
            protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); 
            if(empty || item==null){
                // styled like -fx-prompt-text-fill:
                setStyle("-fx-text-fill: white;");
            } else {
                setStyle("-fx-text-fill: white;");
                setText(item.toString());
            }
        }
        });
        
        if(model.getMissedTotal() != -1) {
            lblMissedTotal.setText(""+model.getMissedTotal());
        }
        
        setTableProperties();
        setTableItems();
        setStatsData();
    }
    
    private void setTableProperties() {
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        colTeacher.setCellValueFactory(new PropertyValueFactory("teacher"));
    }
    
    private void setTableItems() {
        tblMissed.setItems(FXCollections.observableArrayList(model.getMissed()));
    }
    
    private void setStatsData() {
        lblTotal.setText(model.getTotalAttPercent()+"%");
    }
    public void getAllCheckedIn()
    {
        model.getAllCheckedIn();
    }
    
}

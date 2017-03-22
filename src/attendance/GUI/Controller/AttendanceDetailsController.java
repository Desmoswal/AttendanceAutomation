/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentStudent;
import attendance.BE.Schedule;
import attendance.BE.Subject;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
    private Label lblMissedTotal;
    @FXML
    private TableColumn<Schedule, String> colSubject;
    @FXML
    private ComboBox<Subject> cmbCourse;
    
    private AttendanceModel model = new AttendanceModel();
    private CurrentStudent currentStudent = CurrentStudent.getInstance();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lblTotal.setText(""+model.getAllCheckedinForStudent(currentStudent.getId(), currentStudent.getClassid()).size());
        cmbCourse.setButtonCell(new ListCell() {
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
        
        if(model.getMissedSchedulesForStudent(currentStudent.getId(),currentStudent.getClassid()).size() != -1) {
            lblMissedTotal.setText(""+model.getMissedSchedulesForStudent(currentStudent.getId(),currentStudent.getClassid()).size());
        }
        
        setTableProperties();
        setTableItems();
        setStatsData();
        setComboboxItems();
        //setLabels(1002,2,2);
    }
    
    private void setTableProperties() {
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        colTeacher.setCellValueFactory(new PropertyValueFactory("teacher"));
        
        /*cmbCourse.setCellFactory((ListView<Subject> list) -> new ListCell<Subject>(){
            
            @Override
            protected void updateItem(Subject item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        cmbCourse.valueProperty().addListener(new ChangeListener<Subject>(){

            @Override
            public void changed(ObservableValue<? extends Subject> observable, Subject oldValue, Subject newValue) {
                System.out.println("WEEEE'" + newValue.getName());
            }
        });*/
    }
    
    private void setTableItems() {
        tblMissed.setItems(FXCollections.observableArrayList(model.getMissedSchedulesForStudent(currentStudent.getId(),currentStudent.getClassid())));
    }
    
    private void setStatsData() {
        lblTotal.setText(model.getTotalAttPercentForStudent(currentStudent.getId(), currentStudent.getClassid())+"%");
    }
    
    private void setComboboxItems() {
        ArrayList<String> subjectnames = new ArrayList<>();
        /*for (Subject subject : ) {
            subjectnames.add(subject.getName());
        }*/
        cmbCourse.setItems(FXCollections.observableArrayList(model.getSubjectsForStudent(currentStudent.getId())));
        System.out.println("cmb: "+FXCollections.observableArrayList(model.getSubjectsForStudent(currentStudent.getId())));
    }
    public void getAllCheckedIn()
    {
        model.getAllCheckedinForStudent(currentStudent.getId(), currentStudent.getClassid());
    }

    @FXML
    private void comboPickedCourse(ActionEvent event) {
        Subject selected = cmbCourse.getSelectionModel().getSelectedItem();
        setLabels(currentStudent.getId(),currentStudent.getClassid(),selected.getId());
        
    }
    
    private void setLabels(int studentid, int classid, int subjectid) {
        int attended = model.getSubjectCheckinForStudent(studentid, classid, subjectid).size();
        int missed = model.getSubjectMissedForStudent(studentid, classid, subjectid).size();
        
        lblAttendance.setText(""+attended);
        lblMissed.setText(""+missed);
    }
}

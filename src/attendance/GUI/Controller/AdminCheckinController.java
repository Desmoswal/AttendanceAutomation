/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminCheckinController implements Initializable
{

    @FXML
    private Button btnClose;
    @FXML
    private Label lblClass;
    @FXML
    private Label lblCourse;
    @FXML
    private TableView<Student> tblAttending;
    @FXML
    private TableColumn<Student, String> colName;

    private AttendanceModel model = new AttendanceModel();
    private LinkedList<Student> checkinList;
    private LinkedList<CheckBox> checkinboxes = new LinkedList<>();

    private static Schedule thisSchedule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setTableProperties();
        System.out.println(thisSchedule);
        changeCheckedIn();
    }

    private void setTableProperties()
    {
        colName.setCellValueFactory(new PropertyValueFactory("name"));
    }

    public void setTableItems(int classId)
    {
        tblAttending.setItems(FXCollections.observableArrayList(model.getStudentsByClass(classId)));
    }

    @FXML
    private void pressedOnTable(MouseEvent event)
    {
        Student selected = tblAttending.getSelectionModel().getSelectedItem();
        
        ContextMenu contextMenu = new ContextMenu();
        MenuItem checkinItem = new MenuItem("Check-in");
        contextMenu.getItems().add(checkinItem);
        MenuItem deleteCheckinItem = new MenuItem("Delete Check-in");
        contextMenu.getItems().add(deleteCheckinItem);
        
        tblAttending.setContextMenu(contextMenu);
        
        EventHandler checkinEvent = new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modifying Checkin Successful", ButtonType.OK);
                alert.setTitle("Modified checkin");
                alert.setHeaderText("Checked in: " + selected.getName() + " for " + thisSchedule.getSubject());
                alert.show();
                model.adminCheckin(selected, thisSchedule);
                changeCheckedIn();
                System.out.println("Check-in");
            }
        };
        checkinItem.setOnAction(checkinEvent);
        
        EventHandler deleteCheckinEvent = new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Modifying Checkin Successful", ButtonType.OK);
                alert.setTitle("Modified checkin");
                alert.setHeaderText("Deleted checkin for: " + selected.getName());
                alert.show();
                model.deleteCheckin(selected, thisSchedule);
                changeCheckedIn();
                System.out.println("Delete checkin");
            }
        };
        
        deleteCheckinItem.setOnAction(deleteCheckinEvent);
    }

    public void setLabels(int classId, String subject)
    {
        lblClass.setText(model.getClasses().get(classId - 1).getName());
        lblCourse.setText(subject);
    }

    @FXML
    private void closeButtonPressed()
    {
        Stage curStage = (Stage) btnClose.getScene().getWindow();
        curStage.close();
    }

    private void changeCheckedIn()
    {

        tblAttending.setRowFactory(tv -> new TableRow<Student>()
        {
            AttendanceModel model = new AttendanceModel();

            @Override
            public void updateItem(Student item, boolean empty)
            {
                super.updateItem(item, empty);

                if (item == null)
                {
                    setStyle("");
                }
                if (item != null)//avoiding nullpointer exception
                { 
                    List<Integer> checkedins = new ArrayList();
                    for (Schedule schedule : model.getAllCheckedinForStudent(item.getId(), item.getClassid()))
                    {
                        checkedins.add(schedule.getId());
                    }
                    
                    if (checkedins.contains(thisSchedule.getId()))
                    {
                        setStyle("-fx-background-color:rgba(44, 255, 44, 0.5);");
                    }
                }
            }
        });
    }
    

    public static void setThisSchedule(Schedule schedule)
    {
        thisSchedule = schedule;
    }
    
   @FXML
    private void editCheckin()
    {
    }
}

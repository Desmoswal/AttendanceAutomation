/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.BE.Teacher;
import attendance.BLL.ScheduleManager;
import attendance.DAL.SQLConnectionManager;
import java.net.URL;
import java.time.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
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
    }

    public ArrayList<String> getStudents()
    {
        try (Connection con = conManager.getConnection())
        {
            String query = "SELECT Username FROM [Student]";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> students = new ArrayList<>();
            while (rs.next())
            {
                String studentString = "";
                //studentString += rs.getString("id") + " ";
                //studentString +=rs.getString("name") + " ";
                //studentString +=rs.getString("classid");
                studentString += rs.getString("username");
                students.add(studentString);
                //System.out.println(studentString);
            }

            return students;

        } catch (SQLException sqle)
        {
            System.err.println(sqle);
            return null;
        }
    }
    
    private void setTableProperties() {
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colClass.setCellValueFactory(new PropertyValueFactory("classId"));
        colTeacher.setCellValueFactory(new PropertyValueFactory("teacher"));
        colRoom.setCellValueFactory(new PropertyValueFactory("room"));
    }
    
    private void setTableItems() {
        ArrayList<Schedule> schedule = new ArrayList();
        
        //System.out.println(schedule.get(0).getTeacher());
        //System.out.println("---");
        System.out.println(scheduleManager.getSchedules());
        tblCourse.setItems(FXCollections.observableArrayList(scheduleManager.getSchedules()));
    }
}

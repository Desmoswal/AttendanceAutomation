/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class AdminEditSchedulePopupController implements Initializable
{

    @FXML
    private TextField txtCourse;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtRoom;
    private Button btnOk; 
    @FXML
    private TextField txtStartYear;
    @FXML
    private TextField txtStartMonth;
    @FXML
    private TextField txtStartDay;
    @FXML
    private TextField txtStartHour;
    @FXML
    private TextField txtStartMinute;
    @FXML
    private TextField txtEndYear;
    @FXML
    private TextField txtEndMonth;
    @FXML
    private TextField txtEndDay;
    @FXML
    private TextField txtEndHour;
    @FXML
    private TextField txtEndMinute;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
        
    private static Schedule thisSchedule;
    AdminEditScheduleController editSchedController; 
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    public static void setThisSchedule(Schedule schedule)
    {
        thisSchedule = schedule;
    }
    
    
    public void setText()
    {
        //Start Time
        char[] tempStartYear = new char[4];
        String.valueOf(thisSchedule.getStartTime()).getChars(0, 4, tempStartYear, 0);
        char[] tempStartMonth = new char[2];
        String.valueOf(thisSchedule.getStartTime()).getChars(5, 7, tempStartMonth, 0);
        char[] tempStartDay = new char[2];
        String.valueOf(thisSchedule.getStartTime()).getChars(8, 10, tempStartDay, 0);
        char[] tempStartHour = new char[2];
        String.valueOf(thisSchedule.getStartTime()).getChars(11, 13, tempStartHour, 0);
        char[] tempStartMinute = new char[2];
        String.valueOf(thisSchedule.getStartTime()).getChars(14, 16, tempStartMinute, 0);
        
        txtStartYear.setText(String.valueOf(tempStartYear));
        txtStartMonth.setText(String.valueOf(tempStartMonth));
        txtStartDay.setText(String.valueOf(tempStartDay));
        txtStartHour.setText(String.valueOf(tempStartHour));
        txtStartMinute.setText(String.valueOf(tempStartMinute));
        System.out.println("Start time: " + thisSchedule.getStartTime());
        
        //End Time
        char[] tempEndYear = new char[4];
        String.valueOf(thisSchedule.getEndTime()).getChars(0, 4, tempEndYear, 0);
        char[] tempEndMonth = new char[2];
        String.valueOf(thisSchedule.getEndTime()).getChars(5, 7, tempEndMonth, 0);
        char[] tempEndDay = new char[2];
        String.valueOf(thisSchedule.getEndTime()).getChars(8, 10, tempEndDay, 0);
        char[] tempEndHour = new char[2];
        String.valueOf(thisSchedule.getEndTime()).getChars(11, 13, tempEndHour, 0);
        char[] tempEndMinute = new char[2];
        String.valueOf(thisSchedule.getEndTime()).getChars(14, 16, tempEndMinute, 0);
        
        txtEndYear.setText(String.valueOf(tempEndYear));
        txtEndMonth.setText(String.valueOf(tempEndMonth));
        txtEndDay.setText(String.valueOf(tempEndDay));
        txtEndHour.setText(String.valueOf(tempEndHour));
        txtEndMinute.setText(String.valueOf(tempEndMinute));
        System.out.println("End time: " + thisSchedule.getEndTime());
        
        txtCourse.setText(thisSchedule.getClassName());
        txtRoom.setText(thisSchedule.getRoom());
        txtSubject.setText(thisSchedule.getSubject());
    }
    
    public void getText()
    {
        //thisSchedule.setStartTime(String.valueOf(txtStartYear.getText()) + txtStartMonth.getText() + txtStartDay.getText() + txtStartHour.getText() + txtStartMinute.getText());
        thisSchedule.setClassName(txtCourse.getText());
        thisSchedule.setRoom(txtRoom.getText());
        thisSchedule.setSubject(txtSubject.getText());
        //thisSchedule.setTime(txtTime.getText());
    }
    
    public void setController(AdminEditScheduleController c)
    {
        this.editSchedController = c;
    }

    @FXML
    private void onAcceptButtonPressed(ActionEvent event)
    {
        getText();
        //addSchedule();
        Stage curStage = (Stage) btnAccept.getScene().getWindow();
        editSchedController.refreshTable();
        System.out.println("SetTableItems done");
        curStage.close();
    }

    @FXML
    private void onCancelButtonPressed(ActionEvent event)
    {
        Stage curStage = (Stage) btnCancel.getScene().getWindow();
        System.out.println("Cancel button clicked, window closing");
        curStage.close();
    }
}

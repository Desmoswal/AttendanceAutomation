/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentTeacher;
import attendance.BE.Schedule;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
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
    private TextField txtRoom;
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
    private RadioButton rbCancelled;
    @FXML
    private ComboBox<?> cmbCourse;
    @FXML
    private ComboBox<?> cmbSubject;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
        
    private static Schedule thisSchedule;
    AdminEditScheduleController editSchedController; 
    AttendanceModel model = new AttendanceModel();
    CurrentTeacher currentTeacher;
    
      
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
        
        //txtCourse.setText(thisSchedule.getClassName());
        txtRoom.setText(thisSchedule.getRoom());
        //txtSubject.setText(thisSchedule.getSubject());
    }
    
    public void getText()
    {
        Timestamp startTimestamp = new Timestamp(Integer.parseInt(txtStartYear.getText())-1900, Integer.parseInt(txtStartMonth.getText())-1, Integer.parseInt(txtStartDay.getText()), Integer.parseInt(txtStartHour.getText()), Integer.parseInt(txtStartMinute.getText()), 0, 0);
        Timestamp endTimestamp = new Timestamp(Integer.parseInt(txtEndYear.getText())-1900, Integer.parseInt(txtEndMonth.getText())-1, Integer.parseInt(txtEndDay.getText()), Integer.parseInt(txtEndHour.getText()), Integer.parseInt(txtEndMinute.getText()), 0, 0);

        String startTime = txtStartYear.getText() + txtStartMonth.getText() + txtStartDay.getText() + " " + txtStartHour.getText() +":" + txtStartMinute.getText() + ":00";
        String endTime = txtEndYear.getText() + txtEndMonth.getText() + txtEndDay.getText() + " " + txtEndHour.getText() +":" + txtEndMinute.getText() + ":00";

        
        System.out.println(String.valueOf(startTimestamp) + " ---- StartTimeStamp ");
        System.out.println(String.valueOf(endTimestamp) + " ---- endTimeStamp ");
        
        if(thisSchedule != null)
        {
            thisSchedule.setStartTime(startTimestamp);
            thisSchedule.setEndTime(endTimestamp);
            //thisSchedule.setClassName(txtCourse.getText());
            thisSchedule.setRoom(txtRoom.getText());
            //thisSchedule.setSubject(txtSubject.getText());
            //UPDATEMETHOD
            //model.addSchedule(startTime, endTime, 2, 2, txtRoom.getText(), currentTeacher.getId());
        }
        else
        {
            model.addSchedule(startTime, endTime, 2, 2, txtRoom.getText(), currentTeacher.getId());
        }
        
        
        System.out.println("Teacher info: " + currentTeacher.getName());
        System.out.println(currentTeacher.getId());
        
        //GUIDELINE: model.addSchedule(startTime, endTime, "Subject", "ClassId", "Room", "Teacher");
        
    }
    
    public void setCurrentTeacher(CurrentTeacher currentTeacher)
    {
        this.currentTeacher = currentTeacher;
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

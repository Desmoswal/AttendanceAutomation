/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.CurrentTeacher;
import attendance.BE.Schedule;
import attendance.BE.Subject;
import attendance.BE.Class;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
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
    private ComboBox<Class> cmbCourse;
    @FXML
    private ComboBox<Subject> cmbSubject;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnCancel;
        
    private static Schedule thisSchedule;
    private static boolean isNew;
    AdminEditScheduleController editSchedController; 
    AttendanceModel model = new AttendanceModel();
    CurrentTeacher currentTeacher;
    
    private int isCancelled;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cmbCourse.setItems(FXCollections.observableArrayList(model.getClasses()));
        cmbSubject.setItems(FXCollections.observableArrayList(model.getAllSubjects()));   
        
        if(thisSchedule != null && thisSchedule.isCanceled())
        {
            rbCancelled.setSelected(true);
            isCancelled = 1;
        }
        
        if(thisSchedule != null)
        {
            setDateText();
        }
        
        if(isNew)
            rbCancelled.setDisable(true);
    }    
    
    public static void setThisSchedule(Schedule schedule)
    {
        thisSchedule = schedule;
    }
    
    public static void setIsNew(boolean state)
    {
        isNew = state;
    }
    
    private void setDateText()
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
             
        txtRoom.setText(thisSchedule.getRoom());
        
        cmbCourse.getSelectionModel().select(thisSchedule.getClassId()-1);
        //cmbSubject.getSelectionModel().select(thisSchedule.getSubject());
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
            thisSchedule.setClassName(cmbCourse.getSelectionModel().getSelectedItem().getName());
            thisSchedule.setRoom(txtRoom.getText());
            thisSchedule.setSubject(cmbSubject.getSelectionModel().getSelectedItem().getName()); 
            
            if(rbCancelled.isSelected())
            {
                isCancelled = 1;
                thisSchedule.setCanceled(true);
            }
                
            else
                isCancelled = 0;
            
            System.out.println("iscancelled" + isCancelled);
            
            //model.updateSchedule(int id, String startTime, String endTime, int subject, int classId, String room, int teacher, int canceled);
            model.updateSchedule(thisSchedule.getId(), startTime, endTime, cmbSubject.getSelectionModel().getSelectedItem().getId(), cmbCourse.getSelectionModel().getSelectedItem().getId(), txtRoom.getText(), currentTeacher.getId(), isCancelled);
        }
        else
        {
            if(rbCancelled.isSelected())
            {
                isCancelled = 1;
            }
            else
            {
                isCancelled = 0;
            }
            
            model.addSchedule(startTime, endTime, cmbSubject.getSelectionModel().getSelectedItem().getId(), cmbCourse.getSelectionModel().getSelectedItem().getId(), txtRoom.getText(), currentTeacher.getId());
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
        editSchedController.setTableItems();
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

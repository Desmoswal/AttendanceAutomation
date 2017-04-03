/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Schedule;
import attendance.BE.Student;
import attendance.BE.Class;
import attendance.BE.CurrentTeacher;
import attendance.BE.Subject;
import attendance.BLL.SearchHandler;
import attendance.BLL.SearchHandler.SearchType;
import attendance.DAL.AttendanceDetailsManager;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class AdminAttByStudentController implements Initializable
{

    @FXML
    private TableView<Class> tblClasses;
    @FXML
    private TableView<Student> tblStudents;
    @FXML
    private TableColumn<Class, String> colClass;
    @FXML
    private TableColumn<Student, String> colStudent;
    @FXML
    private ComboBox<Subject> cmbStudClassSelect;
    @FXML
    private Label lblStudAvgAtt;
    @FXML
    private Label lblStudAttCourses;
    @FXML
    private Label lblStudMissedCourses;
    @FXML
    private Label lblStudMostMissedWDay;
    @FXML
    private Label lblStudAttPercentSelected;
    @FXML
    private Label lblStudAttSelected;
    @FXML
    private Label lblStudMissedSelected;

    private AttendanceModel model = new AttendanceModel();
    private CurrentTeacher currentTeacher = CurrentTeacher.getInstance();
    
    private AttendanceDetailsManager attdetailsmanager = new AttendanceDetailsManager();
    @FXML
    private ComboBox<String> cmbSearchBox;
    
    private SearchType searchtype;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        cmbStudClassSelect.setButtonCell(new ListCell()
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
        fillComboBox();
        txtSearch.setDisable(true);
        //Load Classes into tableview
        tblClasses.setItems(FXCollections.observableArrayList(model.getClasses()));
    }

    /**
     * setting table's properties
     */
    private void setTableProperties()
    {
        colStudent.setCellValueFactory(new PropertyValueFactory("name"));
        colClass.setCellValueFactory(new PropertyValueFactory("name"));
    }

    @FXML
    private void pressedOnTableClasses(MouseEvent event)
    {
        Class selected = null;

        if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
        {
            selected = tblClasses.getSelectionModel().getSelectedItem();

            //Get Students by selected class
            tblStudents.setItems(FXCollections.observableArrayList(model.getStudentsByClass(selected.getId())));

            System.out.println("Selected Class: " + selected.getId() + " " + selected.getName());
        }
    }

    @FXML
    private void pressedOnTableStudents(MouseEvent event)
    {
        Student selected = null;

        if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
        {
            selected = tblStudents.getSelectionModel().getSelectedItem();
            System.out.println("Selected Student: " + selected.getName());
            System.out.println("Selected Student ID: " + selected.getId());
            lblStudAvgAtt.setText(model.getTotalAttPercentForStudent(selected.getId(), selected.getClassid())+"%");
            lblStudAttCourses.setText(""+model.getAllCheckedinForStudent(selected.getId(), selected.getClassid()).size());
            lblStudMissedCourses.setText(""+model.getMissedSchedulesForStudent(selected.getId(), selected.getClassid()).size());
            lblStudMostMissedWDay.setText(model.getMostMissedDayOfWeekForStudent(selected.getId(), selected.getClassid()));
            
            //--Only after combo box selection
            lblStudAttPercentSelected.setText("");
            lblStudAttSelected.setText("");
            lblStudMissedSelected.setText("");
        
            cmbStudClassSelect.getSelectionModel().clearSelection();
            cmbStudClassSelect.setItems(FXCollections.observableArrayList(model.getSubjectsForStudent(selected.getId())));
            
            if(tblClasses.getItems().isEmpty()) {
                tblClasses.setItems(FXCollections.observableArrayList(model.getClasses()));
            }
            /*for (Class classe : model.getClasses()) {
                if(classe.getId() == selected.getClassid()) {
                    tblClasses.getSelectionModel().select(classe);
                    break;
                }
            }*/
        }
        //--only for test purposes, we just need to change the methods
        /*int missed = model.getMissedSchedulesForStudent(selected.getId(), selected.getClassid()).size();
        System.out.println("Missed Classes: "+ missed);
        
        int attended = model.getAllCheckedinForStudent(selected.getId(), selected.getClassid()).size();
        System.out.println("Attended Classes: "+ attended);
        
        int totalClasses = missed + attended;
        System.out.println("Total classes: " + totalClasses);
        
        float avgAtt = (float)attended / (float)totalClasses * 100 ;*/
        
    }
    
    @FXML
    private void comboPickedCourse(ActionEvent event)
    {
        Subject selected = cmbStudClassSelect.getSelectionModel().getSelectedItem();
        if(selected != null)
        {
            setLabels(tblStudents.getSelectionModel().getSelectedItem().getId(), tblClasses.getSelectionModel().getSelectedItem().getId(), selected.getId());
        }
        
    }
    
    private void setLabels(int studentid, int classid, int subjectid)
    {
        int attended = model.getSubjectCheckinForStudent(studentid, classid, subjectid).size();
        int missed = model.getSubjectMissedForStudent(studentid, classid, subjectid).size();
        
        lblStudAttPercentSelected.setText(model.getTotalAttPercentForSubject(studentid, classid, subjectid) + "%");
        lblStudAttSelected.setText(String.valueOf(attended));
        lblStudMissedSelected.setText(String.valueOf(missed));
    }
    
    private void fillComboBox()
    {try{
        ObservableList<String> comboItems = FXCollections.observableArrayList("Classes","Students");
        cmbSearchBox.setItems(comboItems);
        //cmbSearchBox.getSelectionModel().selectFirst();
    
    } catch (Exception ex)
        {
            //we need the logger so we can see the actual error and not just "FXMLLoader error."
            Logger.getLogger(AdminAttByStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void search(KeyEvent event)
    {
        if(searchtype != null) {
            if(searchtype == SearchType.CLASS) {
                //System.out.println(model.searchClass(txtSearch.getText(),searchtype));
                tblClasses.setItems(FXCollections.observableArrayList(model.searchClass(txtSearch.getText(), model.getClasses(), searchtype)));
            }
            if(searchtype == SearchType.STUDENT) {
                List<Student> students;
                if(tblClasses.getSelectionModel().isEmpty()) {
                    students = model.getStudents();
                } else {
                    students = model.getStudentsByClass(tblClasses.getSelectionModel().getSelectedItem().getId());
                }
                tblStudents.setItems(FXCollections.observableArrayList(model.searchStudent(txtSearch.getText(),students, searchtype)));
            }
        }
    }
    
    @FXML
    private void setSearchType(ActionEvent event)
    {
    if("Students".equals(cmbSearchBox.getSelectionModel().getSelectedItem()))
        {
            this.searchtype = SearchType.STUDENT;
            txtSearch.setDisable(false);
        }
    if("Classes".equals(cmbSearchBox.getSelectionModel().getSelectedItem()))
        {
            this.searchtype = SearchType.CLASS;
            txtSearch.setDisable(false);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.BE.Class;
import attendance.BE.Student;
import attendance.BLL.SearchHandler;
import attendance.BLL.SearchHandler.SearchType;
import attendance.GUI.Model.AttendanceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kristof
 */
public class AdminAttByClassController implements Initializable
{

    @FXML
    private TableView<Class> tblClasses;
    @FXML
    private TableColumn<Class, String> colClass;
    @FXML
    private Label lblAvgAttofClass;
    @FXML
    private Label lblMostMissedCourse;
    @FXML
    private Label lblMostAttendCourse;
    @FXML
    private Label lblMostMissedStud;
    @FXML
    private Label lblMostMissedStudCourse;
    @FXML
    private Label lblMostAttStud;
    @FXML
    private Label lblMostAttStudCourse;
    @FXML
    private TextField txtSearch;
    
    AttendanceModel model = new AttendanceModel();
    
    private SearchHandler.SearchType searchtype = SearchType.CLASS;
    
    Class selected = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO

        setTableProperties();

        //Load Classes into tableview
        tblClasses.setItems(FXCollections.observableArrayList(model.getClasses()));
    }

    private void setTableProperties()
    {
        colClass.setCellValueFactory(new PropertyValueFactory("name"));
    }

    @FXML
    private void pressedOnTableClasses(MouseEvent event)
    {
        //Class selected = null;

        if (event.isPrimaryButtonDown() && event.getClickCount() == 1)
        {
            selected = tblClasses.getSelectionModel().getSelectedItem();

            System.out.println("Selected Class: " + selected.getId() + " " + selected.getName());
            
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Please be patient.");
            alert.setHeaderText("Calculations take several seconds, please be patiente.");
            alert.setContentText("He that can have patience can have what he will.― Benjamin Franklin");
            alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
            alert.show();
            Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            
            calculationTask();
            cancelButton.fire();
        }
    }

    @FXML
    private void search(KeyEvent event)
    {
        if(searchtype != null && (event.getCode().isLetterKey() || event.getCode().isDigitKey() || event.getCode() == KeyCode.BACK_SPACE)) {           
            tblClasses.setItems(FXCollections.observableArrayList(model.doSearch(txtSearch.getText(), model.getClasses(), searchtype)));
        }
    }
    
    public void startTask() 
	{
		// Create a Runnable
		Runnable task = new Runnable()
		{
			public void run()
			{
				calculationTask();
			}
		};

		// Run the task in a background thread
		Thread backgroundThread = new Thread(task);
		// Terminate the running thread if the application exits
		backgroundThread.setDaemon(true);
		// Start the thread
		backgroundThread.start();
	}	
	
	public void calculationTask() 
	{
            lblAvgAttofClass.setText(model.getAvgAttForClass(selected.getId())+"%");//3,48 sec
            lblMostMissedCourse.setText(model.getMostMissedCourseForClass(selected.getId()));//1,93 sec
            lblMostAttendCourse.setText(model.getMostAttCourseForClass(selected.getId()));//1,98 sec
            lblMostMissedStud.setText(model.getMostMissedStudentForClass(selected.getId()).getName());//2,02 sec
            lblMostMissedStudCourse.setText(model.getMostMissedStudentsMostMissedCourse(selected.getId()));//2 sec
            Student student = model.getMostAttStudentForClass(selected.getId());//2 sec
            if(student != null) {
                lblMostAttStud.setText(student.getName());
            } else {
                lblMostAttStud.setText("No attendance data.");
            }
            
            lblMostAttStudCourse.setText(model.getMostAttStudentMostAttCourse(selected.getId()));
        }
}

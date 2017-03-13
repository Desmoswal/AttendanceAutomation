/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import attendance.GUI.Model.AttendanceModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Desmoswal
 */
public class LogInController implements Initializable
{

    @FXML
    private CheckBox chkInvisible;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    private AttendanceModel model = new AttendanceModel();
    private Alert alert;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    @FXML
    private void pressedLogin(ActionEvent event) {
        
        try {
            checkLogin(txtUsername.getText(),txtPassword.getText());
        } catch(IOException e) {
          System.out.println(e);
        }
    }
    
    /**
     * Checks login credetials and opens new window if ok, or creates error popup.
     * @param user
     * @param pass
     * @throws IOException 
     */
    private void checkLogin(String user,String pass) throws IOException {
        
        int loginCode = model.checkLogin(user, pass);
        System.out.println("LoginCode: " + loginCode);
        
           if(loginCode == 0) {
                //login status 0, student login successful
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                FXMLLoader mainloader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/MainView.fxml"));
                //tell usertype to controller BEFORE it loads
                MainViewController.setUserType(MainViewController.UserType.STUDENT);
                Parent root = mainloader.load();
                MainViewController controller = mainloader.getController(); //get the controller for future use

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setMaxWidth(650);
                newStage.show();
                stage.close();
                
            } else if(loginCode == 10) {
                //login status 10, teacher login successful
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                FXMLLoader mainloader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/MainView.fxml"));
                //tell usertype to controler BEFORE it loads
                MainViewController.setUserType(MainViewController.UserType.TEACHER); 
                Parent root = mainloader.load();
                MainViewController controller = mainloader.getController(); //get the controller for future use
                
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
                stage.close();
                
            } else {
                //there were no successful logins, give popup based on statuscode.
                doAlert(loginCode);
            }
    }
    
    /**
     * Creates popup window based on login status. You can see more details of statuscodes in LoginHandler class in BLL.
     * @param status 
     */
    private void doAlert(int status) {
        switch(status) {
            case 1:
                alert = new Alert(AlertType.ERROR, "Invalid username! Maybe a typo?");
                alert.show();
                break;
            case 2:
                alert = new Alert(AlertType.ERROR, "Invalid password! Try again!");
                alert.show();
                break;
            case -1:
                alert = new Alert(AlertType.ERROR, "Empty username!");
                alert.show();
                break;
            case -2:
                alert = new Alert(AlertType.ERROR, "Empty password!");
                alert.show();
                break;
            default: //-3 or anything else
                alert = new Alert(AlertType.ERROR, "Unknown error!");
                alert.show();
                break;
        }
    }
}

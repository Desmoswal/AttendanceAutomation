/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
    private void checkLogin(String user,String pass) throws IOException {
        if(user.equals("student") && !pass.isEmpty()) {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            
            FXMLLoader mainloader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/MainView.fxml"));
            Parent root = mainloader.load();
            
            MainViewController controller = mainloader.getController();
            controller.setUserDemo("student");
            
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            stage.close();
            newStage.show();
        } else if(user.equals("teacher") && !pass.isEmpty()) {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            
            FXMLLoader mainloader = new FXMLLoader(getClass().getResource("/attendance/GUI/View/MainView.fxml"));
            Parent root = mainloader.load();
            
            MainViewController controller = mainloader.getController();
            controller.setUserDemo("admin");
            
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            stage.close();
            newStage.show();
        }
    }
}

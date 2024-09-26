/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package bankproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class HomeController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void custLogin(ActionEvent event) throws IOException {
        Parent customerLogin = FXMLLoader.load(getClass().getResource("CustomerLogin.fxml"));
        Scene customerLoginPage = new Scene(customerLogin);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(customerLoginPage);
        appStage.show();
    }

    @FXML
    private void adLogin(ActionEvent event) throws IOException {
        Parent adminLogin = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene adminLoginPage = new Scene(adminLogin);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(adminLoginPage);
        appStage.show();
    }
    
}

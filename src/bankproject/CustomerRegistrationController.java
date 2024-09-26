/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CustomerRegistrationController implements Initializable {

    @FXML
    private Button btnRegister2;
    @FXML
    private Button btnHome;
    @FXML
    private TextField user_name;
    @FXML
    private TextField address;
    @FXML
    private TextField contact;
    @FXML
    private TextField user_id;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    
    
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bankingsystem","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
       

    @FXML
    private void custRegister(ActionEvent event) throws IOException {
        
        String suser_name,saddress,scontact,suser_id,semail,spassword;
            suser_name = user_name.getText();
            saddress = address.getText();
            scontact = contact.getText();
            suser_id = user_id.getText();
            semail = email.getText();
            spassword = password.getText();
        try 
        {
            pst = con.prepareStatement("insert into customer_user(user_name,address,contact,user_id,password,email)values(?,?,?,?,?,?)");
            pst.setString(1, suser_name);
            pst.setString(2, saddress);
            pst.setString(3, scontact);
            pst.setString(4, suser_id);
            pst.setString(5, spassword);
            pst.setString(6, semail);
            pst.executeUpdate();
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Registation");


            alert.setHeaderText("Customer Registation");
            alert.setContentText("Register Successfully!");

            alert.showAndWait();
            
            Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene homePage = new Scene(home);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(homePage);
            appStage.show();

           
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene homePage = new Scene(home);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(homePage);
        appStage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
    } 
    
}

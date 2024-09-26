/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CustomerLoginController implements Initializable {

    @FXML
    private Button btnlogin1;
    @FXML
    private Button btnregistration1;
    @FXML
    private Button btnhome;
    @FXML
    private TextField user_id;
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
    private void custLogin1(ActionEvent event) throws SQLException, IOException {
        String suser_id,spassword;
         suser_id = user_id.getText();
         spassword = password.getText();
        if (isValidCredentials(suser_id, spassword)) {
            
//            Parent customerhome = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
            Parent root = loader.load();
            CustomerHomeController customerhomeController = loader.getController();
            customerhomeController.setUserDetails(suser_id);
            
            
            Scene customerhomePage = new Scene(root);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(customerhomePage);
            appStage.show();
            
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
            
        } else {
            // Handle incorrect credentials
//            System.out.println("Invalid credentials");

            user_id.setText("");
            password.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);


            alert.setHeaderText("Invalid Credentials!!!");
//            alert.setContentText("Invalid credentials");

            alert.showAndWait();
            
            
        }
    }
    
    private boolean isValidCredentials(String user_id, String password) throws SQLException {
        
        
            String query = "SELECT * FROM customer_user WHERE user_id = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, user_id);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
       
    }

    @FXML
    private void custRegistration1(ActionEvent event) throws IOException {
        Parent customerRegistration = FXMLLoader.load(getClass().getResource("CustomerRegistration.fxml"));
        Scene customerRegistrationPage = new Scene(customerRegistration);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(customerRegistrationPage);
        appStage.show();
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

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
import javafx.stage.Stage;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;


import java.util.ArrayList;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminHomeController implements Initializable {

    @FXML
    private Button btnlogout;
    
    String global_user_id;
    @FXML
    private Text textDatauser;
    @FXML
    private Button chkcrdbtn;
    @FXML
    private Button crtaccbtn;
    @FXML
    private Button apploanbtn;
    @FXML
    private Button closeaccbtn;
    @FXML
    private Button updateadminbtn;
    @FXML
    private Button messagebtn;

    /**
     * Initializes the controller class.
     */
    
    
    public void setUserDetails(String user_id) throws SQLException {
        
//        String query = "SELECT * FROM customer_user WHERE user_id = ?";
//        PreparedStatement statement = con.prepareStatement(query);
//        statement.setString(1, user_id);
//        ResultSet resultSet = statement.executeQuery();
//        String userName = resultSet.getString("user_name");

        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);

        global_user_id = user_id;
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM admin_user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user_id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
//        String userName = resultSet.getString("user_name");
//            String userName = resultSet.getString("name");
//            resultLabel.setText("User Name: " + userName);

                if (resultSet.next()) {
                    String userName = resultSet.getString("user_name");
                    textDatauser.setText("Welcome " + userName);
                } else {
                    
                }
        
//        textDatauser.setText("Welcome, " + userName);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene homePage = new Scene(home);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(homePage);
        appStage.show();
    }

    @FXML
    private void chkcrdBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckCredibility.fxml"));
        Parent root = loader.load();
        CheckCredibilityController checkcredibilityController = loader.getController();
        checkcredibilityController.setUserDetails1(user_id_val);


        Scene checkcredibilityPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(checkcredibilityPage);
        appStage.show();
    }

    @FXML
    private void crtaccBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateCreditAccount.fxml"));
        Parent root = loader.load();
        CreateCreditAccountController createcreditaccountController = loader.getController();
        createcreditaccountController.setUserDetails1(user_id_val);


        Scene createcreditaccountPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(createcreditaccountPage);
        appStage.show();
    }

    @FXML
    private void apploanBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceptlLoanrequest.fxml"));
        Parent root = loader.load();
        AcceptlLoanrequestController acceptlloanrequestController = loader.getController();
        acceptlloanrequestController.setUserDetails1(user_id_val);


        Scene acceptlloanrequestPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(acceptlloanrequestPage);
        appStage.show();
    }

    @FXML
    private void closeaccBtn(ActionEvent event) throws IOException, SQLException {
        
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceptCloseaccount.fxml"));
        Parent root = loader.load();
        AcceptCloseaccountController acceptcloseaccountController = loader.getController();
        acceptcloseaccountController.setUserDetails1(user_id_val);


        Scene acceptcloseaccountPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(acceptcloseaccountPage);
        appStage.show();
    }

    @FXML
    private void updateadminBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAdmindetails.fxml"));
        Parent root = loader.load();
        UpdateAdmindetailsController updateadmindetailsController = loader.getController();
        updateadmindetailsController.setUserDetails1(user_id_val);


        Scene updateadmindetailsPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(updateadmindetailsPage);
        appStage.show();
    }

    @FXML
    private void messageBtn(ActionEvent event) throws IOException, SQLException {
        
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageAdmin.fxml"));
        Parent root = loader.load();
        MessageAdminController messageadminController = loader.getController();
        messageadminController.setUserDetails1(user_id_val);


        Scene messageadminPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(messageadminPage);
        appStage.show();
        
    }
    

    
}

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
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
public class CustomerHomeController implements Initializable {

    @FXML
    private Button btnlogout;
    @FXML
    private Text user_id_text;
    private Text textuserdata;
    private Label userData;
    @FXML
    private Text textDatauser;
    @FXML
    private Button acdetails;
    @FXML
    private Button accreate;
    @FXML
    private Button balcheck;
    @FXML
    private Button tfmoney;
    @FXML
    private Button cashdep;
    @FXML
    private Button clsaccount;
    
    @FXML
    private Button apploanbtn;
    
    String global_user_id;
//    Connection con;
//    PreparedStatement pst;
//    int myIndex;
//    int id;
//    
//    
//    public void Connect()
//    {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost/bankingsystem","root","");
//        } catch (ClassNotFoundException ex) {
//          
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//    
    @FXML
    private Button updatecustomerbtn;
    @FXML
    private Button messagebtn;
    
    
    
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
        String query = "SELECT * FROM customer_user WHERE user_id = ?";
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
    
    
    

    /**
     * Initializes the controller class.
     */
     

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene homePage = new Scene(home);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        appStage.setScene(homePage);
        appStage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//       Connect();
    }   

    @FXML
    private void accountdetails(ActionEvent event) throws IOException, SQLException {
//        Parent customeraccountdetails = FXMLLoader.load(getClass().getResource("CustomerAccountdetails.fxml"));
//        Scene customeraccountdetailsPage = new Scene(customeraccountdetails);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(customeraccountdetailsPage);
//        appStage.show();

        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerAccountdetails.fxml"));
        Parent root = loader.load();
        CustomerAccountdetailsController customeraccountdetailsController = loader.getController();
        customeraccountdetailsController.setUserDetails1(user_id_val);


        Scene customeraccountdetailsPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(customeraccountdetailsPage);
        appStage.show();
    }

    @FXML
    private void accountcreate(ActionEvent event) throws IOException, SQLException {
//        Parent accountdetails = FXMLLoader.load(getClass().getResource("AccountDetails.fxml"));
//        Scene accountdetailsPage = new Scene(accountdetails);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(accountdetailsPage);
//        appStage.show();
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDetails.fxml"));
        Parent root = loader.load();
        AccountDetailsController accountdetailsController = loader.getController();
        accountdetailsController.setUserDetails1(user_id_val);


        Scene accountdetailsPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(accountdetailsPage);
        appStage.show();
    }

    @FXML
    private void balancecheck(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
//        Parent accountbalance = FXMLLoader.load(getClass().getResource("AccountBalance.fxml"));
//        Scene accountbalancePage = new Scene(accountbalance);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(accountbalancePage);
//        appStage.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountBalance.fxml"));
            Parent root = loader.load();
            AccountBalanceController accountbalanceController = loader.getController();
            accountbalanceController.setUserDetails1(user_id_val);
            
            
            Scene customerhomePage = new Scene(root);
            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            appStage.setScene(customerhomePage);
            appStage.show();
    }

    @FXML
    private void transfermoney(ActionEvent event) throws IOException, SQLException {
//        Parent transfermoney = FXMLLoader.load(getClass().getResource("TransferMoney.fxml"));
//        Scene transfermoneyPage = new Scene(transfermoney);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(transfermoneyPage);
//        appStage.show();
        
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransferMoney.fxml"));
        Parent root = loader.load();
        TransferMoneyController transfermoneyController = loader.getController();
        transfermoneyController.setUserDetails1(user_id_val);


        Scene transfermoneyPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(transfermoneyPage);
        appStage.show();
    }

    @FXML
    private void cashdeposit(ActionEvent event) throws IOException, SQLException {
//        Parent cashdeposit = FXMLLoader.load(getClass().getResource("CashDeposit.fxml"));
//        Scene cashdepositPage = new Scene(cashdeposit);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(cashdepositPage);
//        appStage.show();
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CashDeposit.fxml"));
        Parent root = loader.load();
        CashDepositController cashdepositController = loader.getController();
        cashdepositController.setUserDetails1(user_id_val);


        Scene cashdepositPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(cashdepositPage);
        appStage.show();
    }

    @FXML
    private void closeaccount(ActionEvent event) throws IOException, SQLException {
//        Parent closeaccount = FXMLLoader.load(getClass().getResource("CloseAccount.fxml"));
//        Scene closeaccountPage = new Scene(closeaccount);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(closeaccountPage);
//        appStage.show();

        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CloseAccount.fxml"));
        Parent root = loader.load();
        CloseAccountController closeaccountController = loader.getController();
        closeaccountController.setUserDetails1(user_id_val);


        Scene closeaccountPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(closeaccountPage);
        appStage.show();
    }

    @FXML
    private void apploanBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplyLoan.fxml"));
        Parent root = loader.load();
        ApplyLoanController applyloanController = loader.getController();
        applyloanController.setUserDetails1(user_id_val);


        Scene applyloanPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(applyloanPage);
        appStage.show();
    }

    @FXML
    private void updatecustomerBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCustomerdetails.fxml"));
        Parent root = loader.load();
        UpdateCustomerdetailsController updatecustomerdetailsController = loader.getController();
        updatecustomerdetailsController.setUserDetails1(user_id_val);


        Scene updatecustomerdetailsPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(updatecustomerdetailsPage);
        appStage.show();
    }

    @FXML
    private void messageBtn(ActionEvent event) throws IOException, SQLException {
        String user_id_val = global_user_id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageCustomer.fxml"));
        Parent root = loader.load();
        MessageCustomerController messagecustomerController = loader.getController();
        messagecustomerController.setUserDetails1(user_id_val);


        Scene messagecustomerPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(messagecustomerPage);
        appStage.show();
    }

    
    
}

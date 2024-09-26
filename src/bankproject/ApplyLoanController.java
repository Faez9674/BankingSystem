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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ApplyLoanController implements Initializable {

    @FXML
    private Button accntHomebtn;
    
    String user_id_global;
    @FXML
    private ChoiceBox<String> loan_type;
    @FXML
    private ChoiceBox<String> num_year;
    @FXML
    private TextField cust_name;
    @FXML
    private TextField acc_num;
    @FXML
    private TextField loan_amount;
    
    
    
    private String[] loanType = {"Home Loan", "Business Loan", "Personal Loan", "Educational Loan"};
    private String[] numYear = {"1", "2", "3", "4", "5"};
    
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    int myIndex;
    int id;
    @FXML
    private Button loanAppbtn;

    /**
     * Initializes the controller class.
     */
    
    
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
    
    public void setUserDetails1(String user_id_val) throws SQLException, IOException {
        
//        String query = "SELECT * FROM customer_user WHERE user_id = ?";
//        PreparedStatement statement = con.prepareStatement(query);
//        statement.setString(1, user_id);
//        ResultSet resultSet = statement.executeQuery();
//        String userName = resultSet.getString("user_name");

        user_id_global = user_id_val;
        
        
        
        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
//        testData.setText(user_id_val);
        Statement statement = connection.createStatement();
        String query1 = "SELECT * FROM customer_user WHERE user_id = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        preparedStatement1.setString(1, user_id_val);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        if (resultSet1.next()) {
            int user_id = resultSet1.getInt("id");
            String user_name = resultSet1.getString("user_name");
            String user_id_dat = String.valueOf(user_id);
            String query = "SELECT * FROM account_details WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String acc_num1 = resultSet.getString("account_number");
                acc_num.setText(acc_num1);
                acc_num.setEditable(false);
                cust_name.setText(user_name);
                cust_name.setEditable(false);
            } else {
                
            }
        } else {
//            
        }

    }
    
       

    @FXML
    private void accntHomeBtn(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();
        CustomerHomeController customerhomeController = loader.getController();
        customerhomeController.setUserDetails(user_id_global);
        Scene customerhomePage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(customerhomePage);
        appStage.show();
    }
    
    @FXML
    private void loanAppBtn(ActionEvent event) throws IOException {
        
        String scustomer_name,saccount_number,sloan_type,sloan_amount,snum_year;
        scustomer_name = cust_name.getText();
        saccount_number = acc_num.getText();
        sloan_type = loan_type.getValue();
        sloan_amount = loan_amount.getText();
        snum_year = num_year.getValue();
        int saccount_number_data = Integer.parseInt(saccount_number);
        int sloan_amount_data = Integer.parseInt(sloan_amount);
        int snum_year_data = Integer.parseInt(snum_year);
        
        try 
        {
            pst1 = con.prepareStatement("SELECT * FROM loan WHERE account_number = ?");
            pst1.setInt(1, saccount_number_data);
            ResultSet resultSet = pst1.executeQuery();
            
            if(resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");


                alert.setHeaderText("Alert");
                alert.setContentText("Account Already Exist!");

                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
                Parent root = loader.load();
                CustomerHomeController customerhomeController = loader.getController();
                customerhomeController.setUserDetails(user_id_global);
                Scene customerhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(customerhomePage);
                appStage.show();
            }else{
                String status_val = "Pending";
                pst = con.prepareStatement("insert into loan(customer_name,account_number,loan_type,loan_amount,num_year,status)values(?,?,?,?,?,?)");
                pst.setString(1, scustomer_name);
                pst.setInt(2, saccount_number_data);
                pst.setString(3, sloan_type);
                pst.setInt(4, sloan_amount_data);
                pst.setInt(5, snum_year_data);
                pst.setString(6, status_val);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Loan Approval");


                alert.setHeaderText("Loan Approval");
                alert.setContentText("Loan Approved Successfully!");

                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
                Parent root = loader.load();
                CustomerHomeController customerhomeController = loader.getController();
                customerhomeController.setUserDetails(user_id_global);
                Scene customerhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(customerhomePage);
                appStage.show();
            }
            
            
//            Parent customerhome = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
//            Scene customerhomePage = new Scene(customerhome);
//            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//            appStage.setScene(customerhomePage);
//            appStage.show();
            
            

           
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loan_type.getItems().addAll(loanType);
        num_year.getItems().addAll(numYear);
        Connect();
    } 

    
    
}
